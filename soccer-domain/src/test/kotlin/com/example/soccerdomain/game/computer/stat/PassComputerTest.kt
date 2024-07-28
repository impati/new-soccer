package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.player.domain.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


private const val MAXIMUM_PASS = 50
private const val MINIMUM_PASS = 0

class PassComputerTest {

    private val passComputer = PassComputer()

    @Test
    fun highCase() {
        val highCase = StatComputerHelper.highCase(Position.CF)

        passComputer.compute(highCase.playerRecord, highCase.teammate, highCase.opponent)

        println("high ${highCase.playerRecord.pass}")
        assertThat(highCase.playerRecord.pass).isBetween(MINIMUM_PASS, MAXIMUM_PASS)
    }

    @Test
    fun midCase() {
        val midCase = StatComputerHelper.midCase(Position.CF)

        passComputer.compute(midCase.playerRecord, midCase.teammate, midCase.opponent)

        println("mid ${midCase.playerRecord.pass}")
        assertThat(midCase.playerRecord.pass).isBetween(MINIMUM_PASS, MAXIMUM_PASS)
    }

    @Test
    fun compute() {
        val lowCase = StatComputerHelper.lowCase(Position.CF)

        passComputer.compute(lowCase.playerRecord, lowCase.teammate, lowCase.opponent)

        println("low ${lowCase.playerRecord.pass}")
        assertThat(lowCase.playerRecord.pass).isBetween(MINIMUM_PASS, MAXIMUM_PASS)
    }
}
