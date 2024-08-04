package com.example.soccerapi.game.api.response

import com.example.soccerapi.team.api.response.TeamResponse
import com.example.soccerdomain.game.Game
import com.example.soccerdomain.game.GameStatus

data class GameResponse(
    val status: GameStatus,
    val teams: List<TeamResponse>,
    val gameId: Long
) {
    companion object {

        fun from(game: Game): GameResponse {
            return GameResponse(
                status = game.status,
                teams = game.teams.stream().map { TeamResponse.from(it.team) }.toList(),
                gameId = game.id!!
            )
        }
    }
}
