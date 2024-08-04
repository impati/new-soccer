package com.example.soccerapi.game.api.response

import com.example.soccerdomain.game.record.GameRecord

data class GameRecordResponse(
    val teamRecordResponse: TeamRecordResponse,
    val playerRecordResponses: List<PlayerRecordResponse>
) {

    companion object {

        fun from(gameRecord: GameRecord): GameRecordResponse {
            return GameRecordResponse(
                TeamRecordResponse.from(gameRecord.teamRecord),
                gameRecord.playerRecords.stream().map { PlayerRecordResponse.from(it) }.toList()
            )
        }
    }
}
