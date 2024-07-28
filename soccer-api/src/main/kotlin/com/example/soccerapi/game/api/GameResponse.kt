package com.example.soccerapi.game.api

import com.example.soccerdomain.game.Game
import com.example.soccerdomain.game.GameResult
import com.example.soccerdomain.game.record.GameRecord

data class GameResponse(
    val gameRecord: List<GameRecord>,
    val game: Game
) {
    companion object {

        fun from(gameResult: GameResult): GameResponse {
            return GameResponse(gameResult.gameRecords, gameResult.game)
        }
    }
}
