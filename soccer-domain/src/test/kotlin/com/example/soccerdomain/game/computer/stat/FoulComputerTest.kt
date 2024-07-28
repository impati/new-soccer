package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.player.domain.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


private const val MAXIMUM_FOUL = 5
private const val MINIMUM_FOUL = 0

class FoulComputerTest {

    private val foulComputer = FoulComputer()

    @Test
    fun highCase() {
        val highCase = StatComputerHelper.highCase(Position.CF)

        foulComputer.compute(highCase.playerRecord, highCase.teammate, highCase.opponent)

        println("high ${highCase.playerRecord.foul}")
        assertThat(highCase.playerRecord.foul).isBetween(MINIMUM_FOUL, MAXIMUM_FOUL)
    }

    @Test
    fun midCase() {
        val midCase = StatComputerHelper.midCase(Position.CF)

        foulComputer.compute(midCase.playerRecord, midCase.teammate, midCase.opponent)

        println("mid ${midCase.playerRecord.foul}")
        assertThat(midCase.playerRecord.foul).isBetween(MINIMUM_FOUL, MAXIMUM_FOUL)
    }

    @Test
    fun compute() {
        val lowCase = StatComputerHelper.lowCase(Position.CF)

        foulComputer.compute(lowCase.playerRecord, lowCase.teammate, lowCase.opponent)

        println("low ${lowCase.playerRecord.foul}")
        assertThat(lowCase.playerRecord.foul).isBetween(MINIMUM_FOUL, MAXIMUM_FOUL)
    }
}
