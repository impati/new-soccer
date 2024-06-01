package com.example.soccerdomain.player

import com.example.soccerdomain.common.search.NumberOperator
import com.example.soccerdomain.common.search.OrderBy

data class NumberSearchInput(
    val value: Int,
    val operator: NumberOperator,
    val orderBy: OrderBy
)
