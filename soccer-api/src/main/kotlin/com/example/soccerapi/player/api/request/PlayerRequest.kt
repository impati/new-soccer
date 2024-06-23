package com.example.soccerapi.player.api.request

import com.example.soccerdomain.player.domain.*

data class PlayerRequest(
    val name: String,
    val positions: Set<Position>,
    val stat: Stat,
    val traits: Set<Trait>,
    val mainFoot: MainFoot
) {


    fun toEntity(): Player {
        return Player(
            name = name,
            position = positions,
            stat = stat,
            trait = traits,
            mainFoot = mainFoot
        )
    }
}
