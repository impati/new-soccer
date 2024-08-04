package com.example.soccerapi.game.api.response

import com.example.soccerdomain.game.record.TeamRecord

data class TeamRecordResponse(
    val pass: Int,
    val save: Int,
    val foul: Int,
    val freeKick: Int,
    val cornerKick: Int,
    val shotsOnTarget: Int,
    val shots: Int,
    val ballPossession: Int,
    val score: Int,
    val teamName: String,
    val teamId: Long,
) {

    companion object {
        fun from(teamRecord: TeamRecord): TeamRecordResponse {
            return TeamRecordResponse(
                pass = teamRecord.pass,
                save = teamRecord.save,
                foul = teamRecord.foul,
                freeKick = teamRecord.freeKick,
                cornerKick = teamRecord.cornerKick,
                shotsOnTarget = teamRecord.shotsOnTarget,
                shots = teamRecord.shots,
                ballPossession = teamRecord.ballPossession,
                score = teamRecord.score,
                teamName = teamRecord.team.name,
                teamId = teamRecord.team.id!!
            )
        }
    }
}
