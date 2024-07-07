package com.example.soccerdomain.game

import com.example.soccerdomain.game.season.SeasonStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Season(

    val updatedAt: LocalDateTime,

    val createdAt: LocalDateTime,


    @Column(name = "season")
    val value: Int,

    @Column(name = "status")
    var status: SeasonStatus = SeasonStatus.ING,

    @Column(name = "season_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long? = null
) {

    companion object {

        const val START_SEASON = 1;
        fun from(season: Int): Season {
            return Season(LocalDateTime.now(), LocalDateTime.now(), season)
        }
    }

    fun doneSeason() {
        this.status = SeasonStatus.DONE
    }

    fun isPossibleNextSeason(): Boolean {
        return status == SeasonStatus.DONE
    }

    fun nextSeason(): Int {
        return value + 1;
    }
}
