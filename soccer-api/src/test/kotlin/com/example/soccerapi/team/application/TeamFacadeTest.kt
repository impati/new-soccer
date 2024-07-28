package com.example.soccerapi.team.application

import com.example.soccerapi.support.ApiIntegrationTest
import com.example.soccerapi.team.api.request.FormationElementRequest
import com.example.soccerapi.team.api.request.FormationRequest
import com.example.soccerapi.team.api.request.ReleaseRequest
import com.example.soccerapi.team.api.request.ScoutRequest
import com.example.soccerdomain.player.domain.*
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team
import com.example.soccerdomain.team.domain.formation.Formation
import com.example.soccerdomain.team.domain.formation.FormationElement
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple.tuple
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class TeamFacadeTest @Autowired constructor(
    val teamFacade: TeamFacade
) : ApiIntegrationTest() {

    @Test
    fun scout() {
        val team = teamRepository.save(Team(name = "test1", league = League.LALIGA))
        val player = playerRepository.save(
            Player(
                name = "player1",
                positions = setOf(Position.CAM, Position.CDM),
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                traits = setOf(Trait.HEADER),
                mainFoot = MainFoot.BOTH
            )
        )

        teamFacade.scout(team.id!!, ScoutRequest(listOf(player.id!!)))

        assertThat(team.player).hasSize(1)
        assertThat(player.team).isEqualTo(team)
    }

    @Test
    fun release() {
        val team = teamRepository.save(Team(name = "test1", league = League.LEAGUE_1))
        val player = playerRepository.save(
            Player(
                name = "player1",
                positions = setOf(Position.CAM, Position.CDM),
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                traits = setOf(Trait.HEADER),
                mainFoot = MainFoot.BOTH
            )
        )
        team.player.add(player)
        player.join(team)
        clearPersistentContext()

        teamFacade.release(team.id!!, ReleaseRequest(listOf(player.id!!)))

        clearPersistentContext()
        assertThat(teamRepository.findById(team.id!!).orElseThrow().player).isEmpty()
        assertThat(playerRepository.findById(player.id!!).orElseThrow().team).isNull()
    }

    @Test
    fun createFormation() {
        val team = teamRepository.save(Team(name = "test1", league = League.LALIGA))
        val player = playerRepository.save(
            Player(
                name = "player1",
                positions = setOf(Position.CAM, Position.CDM),
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                traits = setOf(Trait.HEADER),
                mainFoot = MainFoot.BOTH
            )
        )
        val request = FormationRequest(
            "formation",
            listOf(FormationElementRequest(Position.CAM, player.id!!))
        )

        val response = teamFacade.createFormation(team.id!!, request)

        assertThat(response.formationId).isNotNull()
        assertThat(response.formationElements)
            .hasSize(1)
            .extracting("playerName", "position")
            .contains(tuple("player1", Position.CAM))
    }

    @Test
    fun editFormation() {
        val team = teamRepository.save(Team(name = "team1", league = League.LALIGA))
        val player1 = playerRepository.save(
            Player(
                name = "player1",
                positions = setOf(Position.CAM, Position.CDM),
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                traits = setOf(Trait.HEADER),
                mainFoot = MainFoot.BOTH
            )
        )
        val player2 = playerRepository.save(
            Player(
                name = "player2",
                positions = setOf(Position.CF),
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                traits = setOf(Trait.MAJOR),
                mainFoot = MainFoot.LEFT
            )
        )
        val formation = Formation("formation1", team)
        formation.addFormationElement(FormationElement(Position.CM, player1, formation))
        formationRepository.save(formation)
        clearPersistentContext()
        val request = FormationRequest(
            "formation2",
            listOf(FormationElementRequest(Position.RB, player2.id!!))
        )

        val response = teamFacade.editFormation(formation.id!!, request)

        assertThat(response.formationId).isNotNull()
        assertThat(response.formationElements)
            .hasSize(1)
            .extracting("playerName", "position")
            .contains(tuple("player2", Position.RB))
    }

    private fun clearPersistentContext() {
        entityManager.flush()
        entityManager.clear()
    }
}

