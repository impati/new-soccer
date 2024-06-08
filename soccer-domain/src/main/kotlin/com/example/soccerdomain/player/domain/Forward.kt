package com.example.soccerdomain.player.domain

import jakarta.persistence.Embeddable

@Embeddable
class Forward(

    val dribble: Int = 0, // 드리블
    val goalDetermination: Int = 0, // 결정력
    val midRangeShot: Int = 0, // 중거리슛
    val shootPower: Int = 0, // 슛파워
    val heading: Int = 0, // 헤딩
)
