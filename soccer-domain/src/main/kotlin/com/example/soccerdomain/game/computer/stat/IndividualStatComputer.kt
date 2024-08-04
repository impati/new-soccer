package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.game.computer.Opponent
import com.example.soccerdomain.game.computer.Teammate
import com.example.soccerdomain.game.record.PlayerRecord

interface IndividualStatComputer : StatComputer {

    fun compute(playerRecord: PlayerRecord, teammate: Teammate, opponent: Opponent)
}
