package com.example.soccerdomain.game.record

import com.example.soccerdomain.game.Game
import com.example.soccerdomain.player.domain.Player
import com.example.soccerdomain.player.domain.Position
import jakarta.persistence.*

@Entity
class PlayerRecord(

    var tackle: Int = 0,

    var intercept: Int = 0,

    var pass: Int = 0,

    var save: Int = 0,

    var foul: Int = 0,

    val freeKick: Int = 0,

    val cornerKick: Int = 0,

    var shotsOnTarget: Int = 0,

    var shots: Int = 0,

    var assist: Int = 0,

    var goal: Int = 0,

    @Enumerated(EnumType.STRING)
    val position: Position,

    @ManyToOne
    @JoinColumn(name = "game_id")
    val game: Game,

    @ManyToOne
    @JoinColumn(name = "team_record_id")
    val teamRecord: TeamRecord,

    @ManyToOne
    @JoinColumn(name = "player_id")
    val player: Player,

    @Column(name = "player_record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long? = null
) {


    companion object {

        fun of(game: Game, teamRecord: TeamRecord, player: Player, position: Position): PlayerRecord {
            return PlayerRecord(game = game, teamRecord = teamRecord, player = player, position = position)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlayerRecord

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
