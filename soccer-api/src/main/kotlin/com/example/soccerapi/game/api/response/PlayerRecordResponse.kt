package com.example.soccerapi.game.api.response

import com.example.soccerdomain.game.record.PlayerRecord
import com.example.soccerdomain.player.domain.Position

data class PlayerRecordResponse(
    val tackle: Int,
    val intercept: Int,
    val pass: Int,
    val save: Int,
    val foul: Int,
    val freeKick: Int,
    val cornerKick: Int,
    val shotsOnTarget: Int,
    val shots: Int,
    val assist: Int,
    val goal: Int,
    val position: Position,
    val playerName: String,
    val playerId: Long,
) {

    companion object {
        fun from(playerRecord: PlayerRecord): PlayerRecordResponse {
            return PlayerRecordResponse(
                tackle = playerRecord.tackle,
                intercept = playerRecord.intercept,
                pass = playerRecord.pass,
                save = playerRecord.save,
                foul = playerRecord.foul,
                freeKick = playerRecord.freeKick,
                cornerKick = playerRecord.cornerKick,
                shotsOnTarget = playerRecord.shotsOnTarget,
                shots = playerRecord.shots,
                assist = playerRecord.assist,
                goal = playerRecord.goal,
                position = playerRecord.position,
                playerName = playerRecord.player.name,
                playerId = playerRecord.player.id!!
            )
        }
    }
}
