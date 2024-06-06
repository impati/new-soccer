package com.example.soccerdomain.player.domain

import jakarta.persistence.Embeddable

@Embeddable
class Physical(

    val acceleration: Int = 0, //기속력
    val speed: Int = 0, //속력
    val physicalFight: Int = 0, //몸싸움
    val stamina: Int = 0, // 체력
    val activeness: Int = 0, // 적극성
    val jump: Int = 0, //점프력
    val balance: Int = 0, // 밸런스 - > 넘어지냐 안넘어지냐
)
