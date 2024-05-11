package com.example.soccer.domain.player

import jakarta.persistence.Embeddable


@Embeddable
class Stat (

    //피지컬
     val acceleration : Int, //기속력
     val speed : Int, //속력
     val physicalFight : Int, //몸싸움

     val stamina : Int, // 체력
     val activeness : Int, // 적극성
     val jump : Int, //점프력
     val balance : Int, // 밸런스 - > 넘어지냐 안넘어지냐


    // 패스
     val ballControl : Int, // 볼컨
     val crosses : Int, // 크로스

     val pass : Int, // 패스
     val longPass : Int, //롱 패스

    //공격력
     val dribble : Int, // 드리블
     val goalDetermination : Int, // 결정력
     val midRangeShot : Int, // 중거리슛
     val shootPower : Int, // 슛파워
     val heading : Int, // 헤딩

    //수비
     val defense : Int, //수비력
     val tackle : Int, // 태클
     val intercepting : Int, // 가로채기
     val slidingTackle : Int, // 슬라이딩 태클

    //골기퍼
     val diving : Int, // 다이빙
     val handling : Int, // 핸들링
     val goalKick : Int, //골킥
     val speedReaction : Int, // 반응속도

    //기타
     val positioning : Int, // 위치선정
     val visualRange : Int, // 시야
     val sense : Int, // 센스

)
