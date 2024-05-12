package com.example.soccerdomain.player.domain

import jakarta.persistence.Embeddable


@Embeddable
class Stat(

    //피지컬
    val acceleration: Int = 0, //기속력
    val speed: Int = 0, //속력
    val physicalFight: Int = 0, //몸싸움

    val stamina: Int = 0, // 체력
    val activeness: Int = 0, // 적극성
    val jump: Int = 0, //점프력
    val balance: Int = 0, // 밸런스 - > 넘어지냐 안넘어지냐


    // 패스
    val ballControl: Int = 0, // 볼컨
    val crosses: Int = 0, // 크로스

    val pass: Int = 0, // 패스
    val longPass: Int = 0, //롱 패스

    //공격력
    val dribble: Int = 0, // 드리블
    val goalDetermination: Int = 0, // 결정력
    val midRangeShot: Int = 0, // 중거리슛
    val shootPower: Int = 0, // 슛파워
    val heading: Int = 0, // 헤딩

    //수비
    val defense: Int = 0, //수비력
    val tackle: Int = 0, // 태클
    val intercepting: Int = 0, // 가로채기
    val slidingTackle: Int = 0, // 슬라이딩 태클

    //골기퍼
    val diving: Int = 0, // 다이빙
    val handling: Int = 0, // 핸들링
    val goalKick: Int = 0, //골킥
    val speedReaction: Int = 0, // 반응속도

    //기타
    val positioning: Int = 0, // 위치선정
    val visualRange: Int = 0, // 시야
    val sense: Int = 0, // 센스

)
