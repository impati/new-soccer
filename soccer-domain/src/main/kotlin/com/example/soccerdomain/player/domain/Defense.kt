package com.example.soccerdomain.player.domain

import jakarta.persistence.Embeddable

@Embeddable
class Defense(
    
    val defense: Int = 0, //수비력
    val tackle: Int = 0, // 태클
    val intercepting: Int = 0, // 가로채기
    val slidingTackle: Int = 0, // 슬라이딩 태클
)
