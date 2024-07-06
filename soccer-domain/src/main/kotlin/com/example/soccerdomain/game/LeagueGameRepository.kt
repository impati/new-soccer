package com.example.soccerdomain.game

import com.example.soccerdomain.team.domain.League
import org.springframework.data.jpa.repository.JpaRepository

interface LeagueGameRepository : JpaRepository<LeagueGame, Long> {


    fun findLeagueGameByLeagueAndSeason(league: League, season: Int): List<LeagueGame>
}
