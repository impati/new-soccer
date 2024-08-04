package com.example.soccerdomain.game

import com.example.soccerdomain.player.domain.Player
import com.example.soccerdomain.team.domain.Team

data class Participate(
    val team: Team,
    val players: List<Player>
)
