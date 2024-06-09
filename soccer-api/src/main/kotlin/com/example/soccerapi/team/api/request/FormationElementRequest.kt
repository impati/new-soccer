package com.example.soccerapi.team.api.request

import com.example.soccerdomain.player.domain.Position

data class FormationElementRequest(

    val position: Position,
    val playerId: Long
)
