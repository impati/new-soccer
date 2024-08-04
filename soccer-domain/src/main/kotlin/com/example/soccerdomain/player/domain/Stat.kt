package com.example.soccerdomain.player.domain

import jakarta.persistence.Embeddable
import kotlin.math.roundToInt


@Embeddable
class Stat(

    val basic: Basic,
    val physical: Physical,
    val pass: Pass,
    val forward: Forward,
    val defense: Defense,
    val goalKeeping: GoalKeeping
) {

    fun passOn(): Int {
        return percent(
            pass.pass * 40
                    + pass.longPass * 15
                    + pass.crosses * 15
                    + basic.visualRange * 15
                    + basic.sense * 10
                    + pass.ballControl * 5
        )
    }

    fun receivePass(): Int {
        return percent(
            physical.activeness * 25
                    + physical.speed * 20
                    + physical.acceleration * 20
                    + basic.positioning * 15
                    + physical.stamina * 10
                    + physical.balance * 5
                    + physical.physicalFight * 4
                    + physical.jump * 1
        );
    }

    fun defensePass(): Int {
        return percent(
            defense.defense * 20
                    + defense.intercepting * 15
                    + defense.tackle * 10
                    + defense.slidingTackle * 7
                    + basic.positioning * 7
                    + physical.acceleration * 7
                    + physical.speed * 7
                    + physical.activeness * 7
                    + basic.visualRange * 6
                    + basic.sense * 6
                    + physical.physicalFight * 6
                    + physical.balance * 2
        )
    }

    fun intercept(): Int {
        return percent(
            defense.intercepting * 20
                    + physical.activeness * 10
                    + physical.stamina * 10
                    + physical.acceleration * 10
                    + physical.speed * 10
                    + basic.positioning * 7
                    + defense.tackle * 7
                    + defense.slidingTackle * 7
                    + basic.visualRange * 7
                    + basic.sense * 7
                    + physical.physicalFight * 3
                    + physical.balance * 2
        )
    }

    fun antiIntercept(): Int {
        return percent(
            physical.speed * 30
                    + basic.sense * 20
                    + physical.acceleration * 10
                    + forward.dribble * 10
                    + basic.positioning * 10
                    + physical.physicalFight * 10
                    + physical.balance * 10
        )
    }

    fun tackle(): Int {
        return percent(
            defense.tackle * 30
                    + defense.defense * 20
                    + physical.activeness * 15
                    + physical.speed * 15
                    + physical.physicalFight * 10
                    + physical.acceleration * 5
                    + physical.balance * 5
        )
    }

    fun antiTackle(): Int {
        return percent(
            physical.physicalFight * 30
                    + physical.stamina * 20
                    + physical.speed * 15
                    + physical.balance * 13
                    + forward.dribble * 12
                    + physical.acceleration * 10
        )
    }

    fun foul(): Int {
        return 100 - percent(
            defense.defense * 50
                    + defense.tackle * 20
                    + defense.slidingTackle * 20
                    + defense.intercepting * 10
        )
    }

    fun shots(): Int {
        return percent(
            +forward.dribble * 15
                    + physical.activeness * 15
                    + physical.speed * 15
                    + physical.acceleration * 10
                    + forward.midRangeShot * 10
                    + forward.heading * 10
                    + basic.positioning * 10
                    + physical.balance * 5
                    + basic.sense * 5
                    + basic.visualRange * 4
                    + physical.jump * 1
        )
    }

    fun antiShots(): Int {
        return percent(
            defense.defense * 20
                    + defense.intercepting * 15
                    + defense.tackle * 15
                    + defense.slidingTackle * 10
                    + physical.activeness * 10
                    + physical.speed * 10
                    + physical.physicalFight * 10
                    + physical.acceleration * 10
        )
    }

    fun shotsOnTarget(): Int {
        return percent(
            forward.goalDetermination * 50
                    + forward.shootPower * 20
                    + physical.balance * 20
                    + basic.sense * 10
        )
    }

    fun saving(): Int {
        return percent(
            goalKeeping.speedReaction * 50
                    + goalKeeping.diving * 30
                    + goalKeeping.handling * 20
        )
    }

    fun assist(): Int {
        return percent(
            pass.pass * 20
                    + pass.crosses * 15
                    + pass.longPass * 15
                    + basic.visualRange * 15
                    + basic.sense * 10
                    + basic.positioning * 5
                    + physical.balance * 5
                    + physical.activeness * 3
                    + pass.ballControl * 2
        )
    }

    private fun percent(value: Int): Int {
        return (value / 100.0).roundToInt();
    }
}
