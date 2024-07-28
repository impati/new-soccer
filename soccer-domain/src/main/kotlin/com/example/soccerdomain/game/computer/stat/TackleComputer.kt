package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.game.computer.Opponent
import com.example.soccerdomain.game.computer.Teammate
import com.example.soccerdomain.game.computer.getOpponentPriority
import com.example.soccerdomain.game.record.PlayerRecord
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import kotlin.math.roundToInt

@Component
@Order(TACKLE)
class TackleComputer : IndividualStatComputer {

    override fun compute(playerRecord: PlayerRecord, teammate: Teammate, opponent: Opponent) {
        var tackle = playerRecord.player.stat.tackle()
        var minusElement = 0
        for (opponentPlayer in opponent.players) {
            val priority = getOpponentPriority(playerRecord.player)
            minusElement -= opponentPlayer.player.stat.antiTackle() * priority[opponentPlayer.position]!!
        }

        tackle += (minusElement / 100.0).roundToInt()
        if (tackle < 0) {
            tackle = 0
        }
        playerRecord.tackle = tackle / 4
    }
}
