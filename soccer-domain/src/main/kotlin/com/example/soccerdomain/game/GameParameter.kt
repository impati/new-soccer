package com.example.soccerdomain.game

import com.example.soccerdomain.team.domain.formation.Formation

data class GameParameter(
    val game: Game,
    val formations: List<Formation>
)
