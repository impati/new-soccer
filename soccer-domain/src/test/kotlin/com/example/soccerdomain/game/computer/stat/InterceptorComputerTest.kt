package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.player.domain.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


private const val MAXIMUM_INTERCEPTOR = 10
private const val MINIMUM_INTERCEPTOR = 0

class InterceptorComputerTest {

    private val interceptorComputer = InterceptorComputer()

    @Test
    fun highCase() {
        val highCase = StatComputerHelper.highCase(Position.CF)

        interceptorComputer.compute(highCase.playerRecord, highCase.teammate, highCase.opponent)

        println("high ${highCase.playerRecord.intercept}")
        assertThat(highCase.playerRecord.intercept).isBetween(MINIMUM_INTERCEPTOR, MAXIMUM_INTERCEPTOR)
    }

    @Test
    fun midCase() {
        val midCase = StatComputerHelper.midCase(Position.CF)

        interceptorComputer.compute(midCase.playerRecord, midCase.teammate, midCase.opponent)

        println("mid ${midCase.playerRecord.intercept}")
        assertThat(midCase.playerRecord.intercept).isBetween(MINIMUM_INTERCEPTOR, MAXIMUM_INTERCEPTOR)
    }

    @Test
    fun compute() {
        val lowCase = StatComputerHelper.lowCase(Position.CF)

        interceptorComputer.compute(lowCase.playerRecord, lowCase.teammate, lowCase.opponent)

        println("low ${lowCase.playerRecord.intercept}")
        assertThat(lowCase.playerRecord.intercept).isBetween(MINIMUM_INTERCEPTOR, MAXIMUM_INTERCEPTOR)
    }
}
