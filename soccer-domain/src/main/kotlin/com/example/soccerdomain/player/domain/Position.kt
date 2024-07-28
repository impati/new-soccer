package com.example.soccerdomain.player.domain

import lombok.Getter

@Getter
enum class Position(name: String) {

    GK("골기퍼"),

    CB("센터백"),
    LB("레프트백"),
    RB("라이트백"),

    CDM("중앙 수비형 미드필더"),
    CM("센터 미드필더"),
    CAM("중앙 공격형 미드필더"),
    LM("레프트 미드필더"),
    RM("라이트 미드필더"),

    LW("레프트 윙"),
    RW("라이트 윙"),
    CF("센터 포워드"),
    ST("스트라이커")
}
