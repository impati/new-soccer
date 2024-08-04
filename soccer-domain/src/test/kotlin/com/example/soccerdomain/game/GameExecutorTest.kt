package com.example.soccerdomain.game

import com.example.soccerdomain.game.computer.GameComputer
import com.example.soccerdomain.game.computer.stat.*
import com.example.soccerdomain.game.league.LeagueGame
import com.example.soccerdomain.game.record.GameRecord
import com.example.soccerdomain.support.FormationFixture
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameExecutorTest {

    private lateinit var gameExecutor: GameExecutor

    private lateinit var gameContextLoader: GameContextLoader

    @BeforeEach
    fun setUp() {
        val individualStatComputers = listOf(
            FoulComputer(),
            InterceptorComputer(),
            PassComputer(),
            SaveComputer(),
            ShotsComputer(),
            ShotsOnTargetComputer(),
            TackleComputer()
        )
        val dependencyStatComputer = listOf(
            GoalComputer(),
            AssistComputer(),
            StatisticsStatComputer()
        )
        gameExecutor = GameExecutor(GameComputer(individualStatComputers, dependencyStatComputer))
        gameContextLoader = GameContextLoader()
    }

    @Test
    fun execute() {
        val teamA = Team(name = "teamA", league = League.LEAGUE_1, id = 1)
        val teamB = Team(name = "teamB", league = League.LEAGUE_1, id = 2)
        val formationA = FormationFixture.create(teamA, "A")
        val formationB = FormationFixture.create(teamB, "B")
        val game = LeagueGame.of(League.LEAGUE_1, 1, 1)
        game.addTeam(teamA, teamB)
        val gameContext = gameContextLoader.load(game, listOf(formationA, formationB))

        val gameResult = gameExecutor.execute(gameContext)

        assertThat(gameResult.gameRecords).hasSize(2)
        assertThat(gameResult.game.status).isEqualTo(GameStatus.FINISHED)
        assertRecord(gameResult.gameRecords[0])
        assertRecord(gameResult.gameRecords[1])
    }

    private fun assertRecord(gameRecord: GameRecord) {
        assertThat(gameRecord.playerRecords.sumOf { it.pass }).isEqualTo(gameRecord.teamRecord.pass)
        assertThat(gameRecord.playerRecords.sumOf { it.save }).isEqualTo(gameRecord.teamRecord.save)
        assertThat(gameRecord.playerRecords.sumOf { it.foul }).isEqualTo(gameRecord.teamRecord.foul)
        assertThat(gameRecord.playerRecords.sumOf { it.freeKick }).isEqualTo(gameRecord.teamRecord.freeKick)
        assertThat(gameRecord.playerRecords.sumOf { it.cornerKick }).isEqualTo(gameRecord.teamRecord.cornerKick)
        assertThat(gameRecord.playerRecords.sumOf { it.shotsOnTarget }).isEqualTo(gameRecord.teamRecord.shotsOnTarget)
        assertThat(gameRecord.playerRecords.sumOf { it.shots }).isEqualTo(gameRecord.teamRecord.shots)
        assertThat(gameRecord.playerRecords.sumOf { it.goal }).isEqualTo(gameRecord.teamRecord.score)
        assertThat(gameRecord.teamRecord.ballPossession).isGreaterThan(0)
    }
}
