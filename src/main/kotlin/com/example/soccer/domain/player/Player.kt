package com.example.soccer.domain.player

import jakarta.persistence.*
import lombok.Getter


@Getter
@Entity
@Table(name = "players")
class Player(

    val name : String,

    @Enumerated(EnumType.STRING)
    val position: Position,

    @Embedded
    val stat : Stat,

    @Enumerated(EnumType.STRING)
    val trait : Trait,

    @Enumerated(EnumType.STRING)
    val mainFoot: MainFoot,

    val rating : Rating,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null
) {
}
