package com.example.soccerdomain.game.record

import com.example.soccerdomain.game.Game
import com.example.soccerdomain.team.domain.Team
import jakarta.persistence.*
import lombok.ToString

@Entity
@ToString
class TeamRecord(

    var pass: Int = 0,

    var save: Int = 0,

    var foul: Int = 0,

    var freeKick: Int = 0,

    var cornerKick: Int = 0,

    var shotsOnTarget: Int = 0,

    var shots: Int = 0,

    var ballPossession: Int = 0,

    var score: Int = 0,

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

    companion object {

        fun of(game: Game, team: Team): TeamRecord {
            return TeamRecord(game = game, team = team)
        }
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TeamRecord

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    fun update(playerRecords: List<PlayerRecord>) {
        this.pass = playerRecords.sumOf { it.pass }
        this.save = playerRecords.sumOf { it.save }
        this.foul = playerRecords.sumOf { it.foul }
        this.freeKick = playerRecords.sumOf { it.freeKick }
        this.cornerKick = playerRecords.sumOf { it.cornerKick }
        this.shotsOnTarget = playerRecords.sumOf { it.shotsOnTarget }
        this.shots = playerRecords.sumOf { it.shots }
    }
}
