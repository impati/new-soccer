package com.example.soccerdomain.player

import com.example.soccerdomain.player.domain.MainFoot
import com.example.soccerdomain.player.domain.Position
import com.example.soccerdomain.player.domain.Trait

data class PlayerSearchInput(

    val name: String? = "",
    val teamName: String? = "",
    val rating: NumberSearchInput? = null,
    val mainFoot: List<MainFoot>? = listOf(),
    val traits: List<Trait>? = listOf(),
    val position: Position? = null
)
