package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.game.computer.Opponent
import com.example.soccerdomain.game.computer.Teammate
import com.example.soccerdomain.game.record.TeamRecord
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(GOAL)
class GoalComputer : DependencyStatComputer {

    override fun compute(teamRecord: TeamRecord, teammate: Teammate, opponent: Opponent) {
        var goal = teammate.players.sumOf { it.shotsOnTarget } - opponent.players.sumOf { it.save }
        while (goal > 0) {
            teammate.players.shuffled().first().goal += 1
            goal -= 1;
        }
    }
}
