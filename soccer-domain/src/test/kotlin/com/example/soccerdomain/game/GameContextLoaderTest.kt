package com.example.soccerdomain.game

import com.example.soccerdomain.game.league.LeagueGame
import com.example.soccerdomain.support.FormationFixture
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameContextLoaderTest {

    private val gameContextLoader = GameContextLoader()

    @Test
    fun load() {
        val teamA = Team(name = "teamA", league = League.LEAGUE_1)
        val teamB = Team(name = "teamB", league = League.LEAGUE_1)
        val formationA = FormationFixture.create(
            teamA,
            "A",
        )
        val formationB = FormationFixture.create(
            teamB,
            "B",
        )
        val game = LeagueGame.of(League.LEAGUE_1, 1, 1)
        game.addTeam(teamA, teamB)
        val gameContext = gameContextLoader.load(
            game = game,
            formations = listOf(formationA, formationB)
        )

        assertThat(gameContext.participates[0]).isEqualTo(
            Participate(
                formationA.team,
                formationA.formationElements.map { it.player }.toList()
            )
        )
        assertThat(gameContext.participates[1]).isEqualTo(
            Participate(
                formationB.team,
                formationB.formationElements.map { it.player }.toList()
            )
        )
    }
}
