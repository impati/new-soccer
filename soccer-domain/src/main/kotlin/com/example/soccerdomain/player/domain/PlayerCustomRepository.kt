package com.example.soccerdomain.player.domain

import com.example.soccerdomain.player.PlayerSearchInput

interface PlayerCustomRepository {

    fun search(playerSearchInput: PlayerSearchInput): List<Player>
}
