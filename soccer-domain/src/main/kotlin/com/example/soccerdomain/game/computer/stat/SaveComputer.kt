package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.game.computer.Opponent
import com.example.soccerdomain.game.computer.Teammate
import com.example.soccerdomain.game.record.PlayerRecord
import com.example.soccerdomain.player.domain.Position
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
@Order(SAVE)
class SaveComputer : IndividualStatComputer {

    override fun compute(playerRecord: PlayerRecord, teammate: Teammate, opponent: Opponent) {
        if (playerRecord.player.position != Position.GK) {
            return
        }

        val iter = Random.nextInt(0, playerRecord.shotsOnTarget + 1)

        for (i in 0..iter) {
            if (Random.nextInt(0, playerRecord.player.stat.saving()) > Random.nextInt(0, 20)) {
                playerRecord.save++
            }
        }
    }
}
