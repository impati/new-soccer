package com.example.soccerdomain.game

import com.example.soccerdomain.team.domain.Team
import jakarta.persistence.*

@Entity
class TeamOfGame(

    @ManyToOne
    @JoinColumn(name = "team_id")
    val team: Team,

    @ManyToOne
    @JoinColumn(name = "game_id")
    val game: Game,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_of_game_id")
    val id: Long? = null
) {


}
