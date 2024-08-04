package com.example.soccerapi.game.api.request

import jakarta.validation.constraints.Size

data class GameRequest(

    @field:Size(min = 2, max = 2)
    val formationIds: List<Long>
)
