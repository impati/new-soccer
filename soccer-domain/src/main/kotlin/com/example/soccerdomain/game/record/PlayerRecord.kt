package com.example.soccerdomain.game.record

import com.example.soccerdomain.game.Game
import com.example.soccerdomain.player.domain.Player
import jakarta.persistence.*

@Entity
class PlayerRecord(

    val tackle: Int,

    val intercept: Int,

    val pass: Int,

    val save: Int,

    val foul: Int,

    val freeKick: Int,

    val cornerKick: Int,

    val shotsOnTarget: Int,

    val shots: Int,

    val assist: Int,

    val goal: Int,


    @ManyToOne
    @JoinColumn(name = "game_id")
    val game: Game,

    @ManyToOne
    @JoinColumn(name = "player_id")
    val player: Player,

    @Column(name = "player_record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long
) {


}
