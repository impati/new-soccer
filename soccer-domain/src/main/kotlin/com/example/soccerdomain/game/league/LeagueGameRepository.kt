package com.example.soccerdomain.game.league

import org.springframework.data.jpa.repository.JpaRepository

interface LeagueGameRepository : JpaRepository<LeagueGame, Long> {


    fun findLeagueGameBySeason(season: Int): List<LeagueGame>
}
