package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.game.computer.Opponent
import com.example.soccerdomain.game.computer.Teammate
import com.example.soccerdomain.game.record.TeamRecord

interface DependencyStatComputer : StatComputer {

    fun compute(teamRecord: TeamRecord, teammate: Teammate, opponent: Opponent)
}
