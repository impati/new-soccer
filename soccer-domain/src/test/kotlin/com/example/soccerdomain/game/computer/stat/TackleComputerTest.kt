package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.player.domain.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private const val MAXIMUM_TACKLE = 10
private const val MINIMUM_TACKLE = 0

class TackleComputerTest {

    private val tackleComputer = TackleComputer()

    @Test
    fun highCase() {
        val highCase = StatComputerHelper.highCase(Position.CF)

        tackleComputer.compute(highCase.playerRecord, highCase.teammate, highCase.opponent)

        println("high ${highCase.playerRecord.tackle}")
        assertThat(highCase.playerRecord.tackle).isBetween(MINIMUM_TACKLE, MAXIMUM_TACKLE)
    }

    @Test
    fun midCase() {
        val midCase = StatComputerHelper.midCase(Position.CF)

        tackleComputer.compute(midCase.playerRecord, midCase.teammate, midCase.opponent)

        println("mid ${midCase.playerRecord.tackle}")
        assertThat(midCase.playerRecord.tackle).isBetween(MINIMUM_TACKLE, MAXIMUM_TACKLE)
    }

    @Test
    fun compute() {
        val lowCase = StatComputerHelper.lowCase(Position.CF)

        tackleComputer.compute(lowCase.playerRecord, lowCase.teammate, lowCase.opponent)

        println("low ${lowCase.playerRecord.tackle}")
        assertThat(lowCase.playerRecord.tackle).isBetween(MINIMUM_TACKLE, MAXIMUM_TACKLE)
    }
}
