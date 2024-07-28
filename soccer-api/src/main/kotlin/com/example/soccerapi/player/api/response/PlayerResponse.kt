package com.example.soccerapi.player.api.response

import com.example.soccerdomain.player.domain.*

data class PlayerResponse(
    val playerId: Long,
    val name: String,
    val teamName: String?,
    val positions: Set<Position>,
    val stat: Stat,
    val traits: Set<Trait>,
    val mainFoot: MainFoot,
    val rating: Int
) {

    companion object {

        fun from(player: Player): PlayerResponse {
            return PlayerResponse(
                player.id!!,
                player.name,
                player.team?.name,
                player.positions,
                player.stat,
                player.traits,
                player.mainFoot,
                player.rating.value
            )
        }
    }
}
