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
@Order(SHOWS)
class ShotsComputer : IndividualStatComputer {

    override fun compute(playerRecord: PlayerRecord, teammate: Teammate, opponent: Opponent) {
        var shots = playerRecord.player.stat.shots() // 100
        var minusElement = 0;
        for (opponentPlayer in opponent.players) { // 11
            val priority = getOpponentPriority(playerRecord.player)
            minusElement += opponentPlayer.player.stat.antiShots() * priority[opponentPlayer.position]!!
        }

        shots -= (minusElement / 80.0).roundToInt()
        if (shots < 0) {
            shots = 0
        }
        playerRecord.shots = Random.nextInt(0, shots + 1) / 3
    }
}
