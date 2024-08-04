package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.game.computer.Opponent
import com.example.soccerdomain.game.computer.Teammate
import com.example.soccerdomain.game.computer.getOpponentPriority
import com.example.soccerdomain.game.record.PlayerRecord
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import kotlin.math.roundToInt
import kotlin.random.Random

@Component
@Order(INTERCEPTOR)
class InterceptorComputer : IndividualStatComputer {

    override fun compute(playerRecord: PlayerRecord, teammate: Teammate, opponent: Opponent) {
        var interceptor = playerRecord.player.stat.intercept()
        var minusElement = 0;
        for (opponentPlayer in opponent.players) {
            val priority = getOpponentPriority(playerRecord.player)
            minusElement -= opponentPlayer.player.stat.antiIntercept() * priority[opponentPlayer.position]!!
        }

        interceptor += (minusElement / 80.0).roundToInt()
        if (interceptor < 0) {
            interceptor = 0
        }
        playerRecord.intercept = Random.nextInt(0, interceptor + 1) / 2
    }
}
