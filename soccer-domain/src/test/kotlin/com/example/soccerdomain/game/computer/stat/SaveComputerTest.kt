package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.player.domain.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


private const val MAXIMUM_SAVE = 10
private const val MINIMUM_SAVE = 0

class SaveComputerTest {

    private val saveComputer = SaveComputer()

    @Test
    fun highCase() {
        val highCase = StatComputerHelper.highCase(Position.CF)
        highCase.playerRecord.shotsOnTarget = 10

        saveComputer.compute(highCase.playerRecord, highCase.teammate, highCase.opponent)

        println("high ${highCase.playerRecord.save}")
        assertThat(highCase.playerRecord.save).isBetween(MINIMUM_SAVE, MAXIMUM_SAVE)
    }

    @Test
    fun midCase() {
        val midCase = StatComputerHelper.midCase(Position.CF)
        midCase.playerRecord.shotsOnTarget = 10

        saveComputer.compute(midCase.playerRecord, midCase.teammate, midCase.opponent)

        println("mid ${midCase.playerRecord.save}")
        assertThat(midCase.playerRecord.save).isBetween(MINIMUM_SAVE, MAXIMUM_SAVE)
    }

    @Test
    fun compute() {
        val lowCase = StatComputerHelper.lowCase(Position.CF)
        lowCase.playerRecord.shotsOnTarget = 10

        saveComputer.compute(lowCase.playerRecord, lowCase.teammate, lowCase.opponent)

        println("low ${lowCase.playerRecord.save}")
        assertThat(lowCase.playerRecord.save).isBetween(MINIMUM_SAVE, MAXIMUM_SAVE)
    }
}
