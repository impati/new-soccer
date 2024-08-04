package com.example.soccerapi.game.api.response

import com.example.soccerdomain.game.GameResult

data class GameResultResponse(
    val gameRecord: List<GameRecordResponse>,
    val game: GameResponse
) {
    companion object {

        fun from(gameResult: GameResult): GameResultResponse {
            return GameResultResponse(
                gameResult.gameRecords.stream().map { GameRecordResponse.from(it) }.toList(),
                GameResponse.from(gameResult.game)
            )
        }
    }
}
