package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.game.computer.Opponent
import com.example.soccerdomain.game.computer.Teammate
import com.example.soccerdomain.game.record.PlayerRecord
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
@Order(FOUL)
class FoulComputer : IndividualStatComputer {

    override fun compute(playerRecord: PlayerRecord, teammate: Teammate, opponent: Opponent) {
        val inter = Random.nextInt(playerRecord.player.stat.foul() / 20 + 1)
        for (i in 0..inter) {
            val n = Random.nextInt(0, 100)
            if (n > 50 && n % 5 == 0) {
                playerRecord.foul++
            }
        }
    }
}
