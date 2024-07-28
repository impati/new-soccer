package com.example.soccerdomain.player.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class StatTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20, 3, 4, 50, 6, 70, 8, 9, 100])
    fun toPass(avg: Int) {
        val stat = getStat(avg)

        val result = stat.passOn()

        assertThat(result).isGreaterThanOrEqualTo(0)
        assertThat(result).isLessThanOrEqualTo(100)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20, 3, 4, 50, 6, 70, 8, 9, 100])
    fun receivePass(avg: Int) {
        val stat = getStat(avg)

        val result = stat.receivePass()

        assertThat(result).isGreaterThanOrEqualTo(0)
        assertThat(result).isLessThanOrEqualTo(100)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20, 3, 4, 50, 6, 70, 8, 9, 100])
    fun defensePass(avg: Int) {
        val stat = getStat(avg)

        val result = stat.defensePass()

        assertThat(result).isGreaterThanOrEqualTo(0)
        assertThat(result).isLessThanOrEqualTo(100)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20, 3, 4, 50, 6, 70, 8, 9, 100])
    fun intercept(avg: Int) {
        val stat = getStat(avg)

        val result = stat.intercept()

        assertThat(result).isGreaterThanOrEqualTo(0)
        assertThat(result).isLessThanOrEqualTo(100)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20, 3, 4, 50, 6, 70, 8, 9, 100])
    fun antiIntercept(avg: Int) {
        val stat = getStat(avg)

        val result = stat.antiIntercept()

        assertThat(result).isGreaterThanOrEqualTo(0)
        assertThat(result).isLessThanOrEqualTo(100)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20, 3, 4, 50, 6, 70, 8, 9, 100])
    fun tackle(avg: Int) {
        val stat = getStat(avg)

        val result = stat.tackle()

        assertThat(result).isGreaterThanOrEqualTo(0)
        assertThat(result).isLessThanOrEqualTo(100)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20, 3, 4, 50, 6, 70, 8, 9, 100])
    fun antiTackle(avg: Int) {
        val stat = getStat(avg)

        val result = stat.antiTackle()

        assertThat(result).isGreaterThanOrEqualTo(0)
        assertThat(result).isLessThanOrEqualTo(100)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20, 3, 4, 50, 6, 70, 8, 9, 100])
    fun foul(avg: Int) {
        val stat = getStat(avg)

        val result = stat.foul()

        assertThat(result).isGreaterThanOrEqualTo(0)
        assertThat(result).isLessThanOrEqualTo(100)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20, 3, 4, 50, 6, 70, 8, 9, 100])
    fun shots(avg: Int) {
        val stat = getStat(avg)

        val result = stat.shots()

        assertThat(result).isGreaterThanOrEqualTo(0)
        assertThat(result).isLessThanOrEqualTo(100)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20, 3, 4, 50, 6, 70, 8, 9, 100])
    fun antiShots(avg: Int) {
        val stat = getStat(avg)

        val result = stat.antiShots()

        assertThat(result).isGreaterThanOrEqualTo(0)
        assertThat(result).isLessThanOrEqualTo(100)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20, 3, 4, 50, 6, 70, 8, 9, 100])
    fun saving(avg: Int) {
        val stat = getStat(avg)

        val result = stat.saving()

        assertThat(result).isGreaterThanOrEqualTo(0)
        assertThat(result).isLessThanOrEqualTo(100)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20, 3, 4, 50, 6, 70, 8, 9, 100])
    fun shotsOnTarget(avg: Int) {
        val stat = getStat(avg)

        val result = stat.shotsOnTarget()

        assertThat(result).isGreaterThanOrEqualTo(0)
        assertThat(result).isLessThanOrEqualTo(100)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 20, 3, 4, 50, 6, 70, 8, 9, 100])
    fun assist(avg: Int) {
        val stat = getStat(avg)

        val result = stat.assist()

        assertThat(result).isGreaterThanOrEqualTo(0)
        assertThat(result).isLessThanOrEqualTo(100)
    }

    private fun getStat(avg: Int): Stat {
        return Stat(
            basic = Basic(
                positioning = avg,
                visualRange = avg,
                sense = avg
            ),
            physical = Physical(
                physicalFight = avg,
                acceleration = avg,
                activeness = avg,
                balance = avg,
                speed = avg,
                stamina = avg,
                jump = avg
            ),
            pass = Pass(
                pass = avg,
                longPass = avg,
                crosses = avg,
                ballControl = avg
            ),
            forward = Forward(
                dribble = avg,
                goalDetermination = avg,
                midRangeShot = avg,
                shootPower = avg,
                heading = avg
            ),
            defense = Defense(
                intercepting = avg,
                tackle = avg,
                slidingTackle = avg,
                defense = avg
            ),
            goalKeeping = GoalKeeping(
                diving = avg,
                handling = avg,
                goalKick = avg,
                speedReaction = avg,
            )
        )
    }
}
