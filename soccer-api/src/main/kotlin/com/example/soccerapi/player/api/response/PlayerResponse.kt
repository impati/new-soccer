package com.example.soccerapi.player.api.response

import com.example.soccerdomain.player.domain.*

data class PlayerResponse(
    val playerId: Long,
    val name: String,
    val position: Set<Position>,
    val stat: Stat,
    val trait: Set<Trait>,
    val mainFoot: MainFoot,
    val rating: Int
) {

    companion object {

        fun from(player: Player): PlayerResponse {
            return PlayerResponse(
                player.id!!,
                player.name,
                player.position,
                player.stat,
                player.trait,
                player.mainFoot,
                player.rating.value
            )
        }
    }
}
