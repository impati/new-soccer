package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.game.computer.Opponent
import com.example.soccerdomain.game.computer.Teammate
import com.example.soccerdomain.game.computer.getOpponentPriority
import com.example.soccerdomain.game.computer.getTeammatePriority
import com.example.soccerdomain.game.record.PlayerRecord
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import kotlin.math.roundToInt

@Component
@Order(PASS)
class PassComputer : IndividualStatComputer {

    override fun compute(playerRecord: PlayerRecord, teammate: Teammate, opponent: Opponent) {
        playerRecord.pass = playerRecord.player.stat.passOn()
        var plusElement = 0;
        for (teamPlayer in teammate.players) {
            val priority = getTeammatePriority(playerRecord.player)
            plusElement += teamPlayer.player.stat.receivePass() * priority[teamPlayer.position]!!
        }

        var minusElement = 0;
        for (opponentPlayer in opponent.players) {
            val priority = getOpponentPriority(playerRecord.player)
            minusElement -= opponentPlayer.player.stat.defensePass() * priority[opponentPlayer.position]!!
        }
        playerRecord.pass += (plusElement / 100.0).roundToInt() + (minusElement / 100.0).roundToInt()
        if (playerRecord.pass < 0) {
            playerRecord.pass = 0
        }
        playerRecord.pass /= 5;
    }
}
