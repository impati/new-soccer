package com.example.soccerdomain.game.record

import com.example.soccerdomain.game.Game
import com.example.soccerdomain.team.domain.Team
import jakarta.persistence.*

@Entity
class TeamRecord(

    val pass: Int = 0,

    val save: Int = 0,

    val foul: Int = 0,

    val freeKick: Int = 0,

    val cornerKick: Int = 0,

    val shotsOnTarget: Int = 0,

    val shots: Int = 0,

    val ballPossession: Int = 0,

    @ManyToOne
    @JoinColumn(name = "game_id")
    val game: Game,

    @ManyToOne
    @JoinColumn(name = "team_id")
    val team: Team,

    @Column(name = "team_record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long? = null,
) {
}
