package com.example.soccerdomain.game

import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@DiscriminatorValue("league")
class LeagueGame(

    @Column(name = "updated_at", nullable = false, updatable = true)
    val updatedAt: LocalDateTime,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime,

    @Column(name = "league")
    @Enumerated(EnumType.STRING)
    val league: League,

    @Column(name = "season")
    val season: Int,

    @Column(name = "round")
    val round: Int,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: LeagueGameStatus = LeagueGameStatus.BEFORE,

    @Transient
    val teams: MutableList<Team> = mutableListOf(),

    id: Long? = null,
) : Game() {


    companion object {

        fun of(league: League, season: Int, round: Int): LeagueGame {
            return LeagueGame(
                updatedAt = LocalDateTime.now(),
                createdAt = LocalDateTime.now(),
                league = league,
                season = season,
                round = round
            )
        }
    }

    fun addTeam(home: Team, away: Team) {
        this.teams.add(home)
        this.teams.add(away)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LeagueGame

        if (league != other.league) return false
        if (season != other.season) return false
        if (round != other.round) return false

        return true
    }

    override fun hashCode(): Int {
        var result = league.hashCode()
        result = 31 * result + season
        result = 31 * result + round
        return result
    }

    override fun toString(): String {
        return "LeagueGame(league=$league, season=$season, round=$round)"
    }
}
