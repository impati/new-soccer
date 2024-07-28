package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.player.domain.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private const val MAXIMUM_SHOTS_ON_TARGET = 20
private const val MINIMUM_SHOTS_ON_TARGET = 0

class ShotsOnTargetComputerTest {

    private val shotsOnTargetComputer = ShotsOnTargetComputer()

    @Test
    fun highCase() {
        val highCase = StatComputerHelper.highCase(Position.CF)
        highCase.playerRecord.shots = 10

        shotsOnTargetComputer.compute(highCase.playerRecord, highCase.teammate, highCase.opponent)

        println("high ${highCase.playerRecord.shotsOnTarget}")
        assertThat(highCase.playerRecord.shotsOnTarget).isBetween(MINIMUM_SHOTS_ON_TARGET, MAXIMUM_SHOTS_ON_TARGET)
    }

    @Test
    fun midCase() {
        val midCase = StatComputerHelper.midCase(Position.CF)
        midCase.playerRecord.shots = 10

        shotsOnTargetComputer.compute(midCase.playerRecord, midCase.teammate, midCase.opponent)

        println("mid ${midCase.playerRecord.shotsOnTarget}")
        assertThat(midCase.playerRecord.shotsOnTarget).isBetween(MINIMUM_SHOTS_ON_TARGET, MAXIMUM_SHOTS_ON_TARGET)
    }

    @Test
    fun compute() {
        val lowCase = StatComputerHelper.lowCase(Position.CF)
        lowCase.playerRecord.shots = 10

        shotsOnTargetComputer.compute(lowCase.playerRecord, lowCase.teammate, lowCase.opponent)

        println("low ${lowCase.playerRecord.shotsOnTarget}")
        assertThat(lowCase.playerRecord.shotsOnTarget).isBetween(MINIMUM_SHOTS_ON_TARGET, MAXIMUM_SHOTS_ON_TARGET)
    }
}
