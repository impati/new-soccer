package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.game.computer.Opponent
import com.example.soccerdomain.game.computer.Teammate
import com.example.soccerdomain.game.record.TeamRecord
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(BALL)
class StatisticsStatComputer : DependencyStatComputer {
    override fun compute(teamRecord: TeamRecord, teammate: Teammate, opponent: Opponent) {
        val teamPass = teammate.players.sumOf { it.pass }
        val opponentPass = opponent.players.sumOf { it.pass }
        with(teamRecord) {
            ballPossession = (teamPass * 100) / (teamPass + opponentPass)
            pass = teammate.players.sumOf { it.pass }
            score = teammate.players.sumOf { it.goal }
            foul = teammate.players.sumOf { it.foul }
            cornerKick = teammate.players.sumOf { it.cornerKick }
            freeKick = teammate.players.sumOf { it.freeKick }
            save = teammate.players.sumOf { it.save }
            shots = teammate.players.sumOf { it.shots }
            shotsOnTarget = teammate.players.sumOf { it.shotsOnTarget }
        }
    }
}
