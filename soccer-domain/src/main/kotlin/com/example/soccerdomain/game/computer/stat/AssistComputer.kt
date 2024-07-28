package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.game.computer.Opponent
import com.example.soccerdomain.game.computer.Teammate
import com.example.soccerdomain.game.computer.getTeammatePriority
import com.example.soccerdomain.game.record.PlayerRecord
import com.example.soccerdomain.game.record.TeamRecord
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
@Order(ASSIST)
class AssistComputer : DependencyStatComputer {
    override fun compute(teamRecord: TeamRecord, teammate: Teammate, opponent: Opponent) {
        for (playerRecord in teammate.players) {
            for (i in 0 until playerRecord.goal) {
                decisionAssist(playerRecord, teammate)
            }
        }
    }

    private fun decisionAssist(playerRecord: PlayerRecord, teammate: Teammate) {
        val teammatePriority = getTeammatePriority(playerRecord.player)
        val priority = teammate.players.sortedBy { it.player.stat.assist() * teammatePriority[it.position]!! }
            .reversed()
            .subList(0, 5)

        for (player in priority) {
            if (Random.nextInt(0, 100) > 80) {
                player.assist += 1
                return
            }
        }
    }
}
