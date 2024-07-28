package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.player.domain.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StatisticsStatComputerTest {

    private val statisticsStatComputer = StatisticsStatComputer()

    @Test
    fun `should be annotated with Order`() {
        val highCase = StatComputerHelper.highCase(Position.CF)
        val teamRecord = highCase.playerRecord.teamRecord
        val teammate = highCase.teammate
        val opponent = highCase.opponent
        teammate.players.forEach { player ->
            player.pass = 10
        }
        opponent.players.forEach { player ->
            player.pass = 20
        }

        statisticsStatComputer.compute(teamRecord, teammate, opponent)

        assertThat(teamRecord.ballPossession).isEqualTo(33);
    }
}
