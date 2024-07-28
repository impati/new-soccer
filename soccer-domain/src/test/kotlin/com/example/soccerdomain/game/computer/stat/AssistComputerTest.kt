package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.player.domain.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AssistComputerTest {

    private val assistComputer = AssistComputer()

    @Test
    fun `should compute assist`() {
        val highCase = StatComputerHelper.highCase(Position.CF)
        val teamRecord = highCase.playerRecord.teamRecord
        val teammate = highCase.teammate
        val opponent = highCase.opponent
        teammate.players.first().goal = 10

        assistComputer.compute(teamRecord, teammate, opponent)

        println(" assist ${teammate.players.sumOf { it.assist }}")
        assertThat(teammate.players.sumOf { it.assist }).isLessThanOrEqualTo(10)
    }
}
