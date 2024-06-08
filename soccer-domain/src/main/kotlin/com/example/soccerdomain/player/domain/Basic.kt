package com.example.soccerdomain.player.domain

import jakarta.persistence.Embeddable

@Embeddable
class Basic(

    val positioning: Int = 0, // 위치선정
    val visualRange: Int = 0, // 시야
    val sense: Int = 0, // 센스
)
