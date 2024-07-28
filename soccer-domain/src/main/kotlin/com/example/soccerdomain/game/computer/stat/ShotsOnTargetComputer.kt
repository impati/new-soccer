package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.game.computer.Opponent
import com.example.soccerdomain.game.computer.Teammate
import com.example.soccerdomain.game.record.PlayerRecord
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
@Order(SHOTS_ON_TARGET)
class ShotsOnTargetComputer : IndividualStatComputer {

    override fun compute(playerRecord: PlayerRecord, teammate: Teammate, opponent: Opponent) {
        val inter = Random.nextInt(0, playerRecord.shots + 1)

        for (i in 0..inter) {
            if (Random.nextInt(0, playerRecord.player.stat.shotsOnTarget() + 1) > Random.nextInt(0, 50)) {
                playerRecord.shotsOnTarget++
            }
        }
    }
}
