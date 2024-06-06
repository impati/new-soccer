package com.example.soccerdomain.player.domain

import jakarta.persistence.Embeddable

@Embeddable
class GoalKeeping(

    val diving: Int = 0, // 다이빙
    val handling: Int = 0, // 핸들링
    val goalKick: Int = 0, //골킥
    val speedReaction: Int = 0, // 반응속도
)
