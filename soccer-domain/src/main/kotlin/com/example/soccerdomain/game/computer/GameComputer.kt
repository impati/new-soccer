package com.example.soccerdomain.game.computer

import com.example.soccerdomain.game.computer.stat.DependencyStatComputer
import com.example.soccerdomain.game.computer.stat.IndividualStatComputer
import com.example.soccerdomain.game.record.PlayerRecord
import com.example.soccerdomain.game.record.TeamRecord
import org.springframework.stereotype.Component

@Component
class GameComputer(
    val individualStatComputers: List<IndividualStatComputer>,
    val dependencyStatComputer: List<DependencyStatComputer>
) {

    fun compute(playerRecord: PlayerRecord, teammate: Teammate, opponent: Opponent) {
        individualStatComputers.forEach { it.compute(playerRecord, teammate, opponent) }
    }

    fun compute(teamRecord: TeamRecord, teammate: Teammate, opponent: Opponent) {
        dependencyStatComputer.forEach { it.compute(teamRecord, teammate, opponent) }
    }
}
