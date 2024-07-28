package com.example.soccerdomain.game

import com.example.soccerdomain.team.domain.Team
import jakarta.persistence.*


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
open class Game(

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: GameStatus = GameStatus.BEFORE,

    @OneToMany(mappedBy = "game", cascade = [CascadeType.ALL], orphanRemoval = true)
    val teams: MutableList<TeamOfGame> = mutableListOf(),

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    @Id
    val id: Long? = null
) {

    fun addTeam(home: Team, away: Team) {
        this.teams.add(TeamOfGame(home, this))
        this.teams.add(TeamOfGame(away, this))
    }

    fun finish() {
        this.status = GameStatus.FINISHED
    }

    fun start() {
        this.status = GameStatus.ING
    }
}
