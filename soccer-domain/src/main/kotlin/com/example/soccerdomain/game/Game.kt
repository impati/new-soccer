package com.example.soccerdomain.game

import com.example.soccerdomain.team.domain.Team
import jakarta.persistence.*


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
open class Game(

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: GameStatus = GameStatus.BEFORE,

    @Transient
    val teams: MutableList<Team> = mutableListOf(),

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    @Id
    val id: Long? = null
)
