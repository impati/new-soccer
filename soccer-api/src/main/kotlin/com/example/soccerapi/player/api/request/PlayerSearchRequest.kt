package com.example.soccerapi.player.api.request

import com.example.soccerdomain.common.search.NumberOperator
import com.example.soccerdomain.common.search.OrderBy
import com.example.soccerdomain.player.NumberSearchInput
import com.example.soccerdomain.player.PlayerSearchInput
import com.example.soccerdomain.player.domain.MainFoot
import com.example.soccerdomain.player.domain.Position
import com.example.soccerdomain.player.domain.Trait

data class PlayerSearchRequest(

    val name: String? = "",
    val teamName: String? = "",
    val rating: NumberSearchRequest? = null,
    val mainFoot: List<MainFoot>?,
    val trait: List<Trait>?,
    val position: Position? = null
) {
    fun toInput(): PlayerSearchInput {
        return PlayerSearchInput(
            name = name,
            teamName = teamName,
            rating = rating?.toInput(),
            mainFoot = mainFoot,
            trait = trait,
            position = position
        )
    }

    data class NumberSearchRequest(
        val value: Int,
        val operator: NumberOperator,
        val orderBy: OrderBy
    ) {
        fun toInput(): NumberSearchInput {
            return NumberSearchInput(
                value = value,
                operator = operator,
                orderBy = orderBy
            )
        }
    }
}
