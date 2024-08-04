package com.example.soccerdomain.game.record

import com.example.soccerdomain.game.computer.Opponent
import com.example.soccerdomain.game.computer.Teammate
import com.example.soccerdomain.team.domain.Team

class GameRecords(
    val gameRecords: List<GameRecord>
) {

    fun getTeamMate(team: Team): Teammate {
        return Teammate(gameRecords.filter { it.teamRecord.team == team }.map { it.playerRecords }.first())
    }

    fun getOpponent(team: Team): Opponent {
        return Opponent(gameRecords.filter { it.teamRecord.team != team }.map { it.playerRecords }.first())
    }
}
