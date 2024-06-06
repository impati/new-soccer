package com.example.soccerdomain.player.domain

import jakarta.persistence.Embeddable

@Embeddable
class Pass(

    val ballControl: Int = 0, // 볼컨
    val crosses: Int = 0, // 크로스
    val pass: Int = 0, // 패스
    val longPass: Int = 0, //롱 패스
)
