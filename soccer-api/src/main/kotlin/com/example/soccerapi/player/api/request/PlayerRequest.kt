package com.example.soccerapi.player.api.request

import com.example.soccerdomain.player.domain.*

data class PlayerRequest(
    val name: String,
    val position: Set<Position>,
    val stat: Stat,
    val trait: Set<Trait>,
    val mainFoot: MainFoot
) {


    fun toEntity(): Player {
        return Player(
            name = name,
            position = position,
            stat = stat,
            trait = trait,
            mainFoot = mainFoot
        )
    }
}
