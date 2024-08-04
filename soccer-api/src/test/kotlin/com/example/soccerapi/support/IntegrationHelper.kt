package com.example.soccerapi.support

import com.example.soccerapi.team.api.request.FormationElementRequest
import com.example.soccerapi.team.api.request.FormationRequest
import com.example.soccerapi.team.api.request.ScoutRequest
import com.example.soccerapi.team.application.TeamFacade
import com.example.soccerdomain.player.domain.*
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team
import com.example.soccerdomain.team.domain.TeamRepository
import org.springframework.stereotype.Component

@Component
class IntegrationHelper(
    private val teamRepository: TeamRepository,
    private val playerRepository: PlayerRepository,
    private val teamFacade: TeamFacade,
) {


    fun createTeamWithPlayers(): Team {
        val team = teamRepository.save(Team(league = League.LEAGUE_1, name = "A"))
        val player1 = playerRepository.save(createPlayer(Position.ST))
        val player2 = playerRepository.save(createPlayer(Position.RW))
        val player3 = playerRepository.save(createPlayer(Position.LW))
        val player4 = playerRepository.save(createPlayer(Position.RM))
        val player5 = playerRepository.save(createPlayer(Position.LM))
        val player6 = playerRepository.save(createPlayer(Position.CAM))
        val player7 = playerRepository.save(createPlayer(Position.CDM))
        val player8 = playerRepository.save(createPlayer(Position.LB))
        val player9 = playerRepository.save(createPlayer(Position.RB))
        val player10 = playerRepository.save(createPlayer(Position.CB))
        val player11 = playerRepository.save(createPlayer(Position.GK))
        val scoutRequest = ScoutRequest(
            listOf(
                player1.id!!,
                player2.id!!,
                player3.id!!,
                player4.id!!,
                player5.id!!,
                player6.id!!,
                player7.id!!,
                player8.id!!,
                player9.id!!,
                player10.id!!,
                player11.id!!
            )
        )
        teamFacade.scout(team.id!!, scoutRequest);
        val formationRequest = FormationRequest(
            "test",
            listOf(
                FormationElementRequest(player1.position!!, player1.id!!),
                FormationElementRequest(player2.position!!, player2.id!!),
                FormationElementRequest(player3.position!!, player3.id!!),
                FormationElementRequest(player4.position!!, player4.id!!),
                FormationElementRequest(player5.position!!, player5.id!!),
                FormationElementRequest(player6.position!!, player6.id!!),
                FormationElementRequest(player7.position!!, player7.id!!),
                FormationElementRequest(player8.position!!, player8.id!!),
                FormationElementRequest(player9.position!!, player9.id!!),
                FormationElementRequest(player10.position!!, player10.id!!),
                FormationElementRequest(player11.position!!, player11.id!!)
            )
        )
        teamFacade.createFormation(team.id!!, formationRequest)
        return team
    }

    private fun createPlayer(position: Position) = Player(
        name = position.name,
        position = position,
        stat = getStat(),
        positions = setOf(position),
        traits = emptySet(),
        mainFoot = MainFoot.BOTH
    )

    private fun getStat(): Stat {
        return Stat(
            Basic(50, 50, 50),
            Physical(50, 50, 50, 50, 50, 50, 50),
            Pass(50, 50, 50, 50),
            Forward(50, 50, 50, 50, 50),
            Defense(50, 50, 50, 50),
            GoalKeeping(50, 50, 50, 50)
        )
    }
}
