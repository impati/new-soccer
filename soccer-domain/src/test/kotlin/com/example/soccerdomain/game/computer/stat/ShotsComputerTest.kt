package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.player.domain.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private const val MAXIMUM_SHOTS = 20
private const val MINIMUM_SHOTS = 0

class ShotsComputerTest {

    private val shotsComputer = ShotsComputer()

    @Test
    fun highCase() {
        val highCase = StatComputerHelper.highCase(Position.CF)

        shotsComputer.compute(highCase.playerRecord, highCase.teammate, highCase.opponent)

        println("high ${highCase.playerRecord.shots}")
        assertThat(highCase.playerRecord.shots).isBetween(MINIMUM_SHOTS, MAXIMUM_SHOTS)
    }

    @Test
    fun midCase() {
        val midCase = StatComputerHelper.midCase(Position.CF)

        shotsComputer.compute(midCase.playerRecord, midCase.teammate, midCase.opponent)

        println("mid ${midCase.playerRecord.shots}")
        assertThat(midCase.playerRecord.shots).isBetween(MINIMUM_SHOTS, MAXIMUM_SHOTS)
    }

    @Test
    fun compute() {
        val lowCase = StatComputerHelper.lowCase(Position.CF)

        shotsComputer.compute(lowCase.playerRecord, lowCase.teammate, lowCase.opponent)

        println("low ${lowCase.playerRecord.shots}")
        assertThat(lowCase.playerRecord.shots).isBetween(MINIMUM_SHOTS, MAXIMUM_SHOTS)
    }
}
