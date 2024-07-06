package com.example.soccerdomain.game

import jakarta.persistence.*


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
open class Game(


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    @Id
    val id: Long? = null
) {
}
