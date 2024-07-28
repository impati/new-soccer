package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.player.domain.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private const val MAXIMUM_GOAL = 10
private const val MINIMUM_GOAL = 0


class GoalComputerTest {

    private val goalComputer = GoalComputer()

    @Test
    fun `should compute goal`() {
        val highCase = StatComputerHelper.highCase(Position.CF)
        val teamRecord = highCase.playerRecord.teamRecord
        val teammate = highCase.teammate
        val opponent = highCase.opponent
        teammate.players.forEach { it.shotsOnTarget = 1 }
        opponent.players.filter { it.position == Position.GK }.forEach { it.save = 5 }

        goalComputer.compute(teamRecord, teammate, opponent)

        assertThat(teamRecord.score).isEqualTo(5)
        assertThat(teammate.players.sumOf { it.goal }).isEqualTo(5)
    }
}
