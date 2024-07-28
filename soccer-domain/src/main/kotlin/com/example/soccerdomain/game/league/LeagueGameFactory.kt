package com.example.soccerdomain.game.league

import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team
import com.example.soccerdomain.team.domain.TeamRepository
import org.springframework.stereotype.Component

@Component
class LeagueGameFactory(
    val teamRepository: TeamRepository
) {

    fun create(season: Int): List<LeagueGame> {
        return League.entries.map { create(it, season) }.flatten()
    }

    fun create(league: League, season: Int): List<LeagueGame> {
        val teams = teamRepository.findTeamByLeague(league)
        val leagueGames = mutableListOf<LeagueGame>()
        val rotateTeams = teams.drop(1).toMutableList()

        for (round in 0 until teams.size - 1) {
            leagueGames.add(
                createLeagueGameBy(league, season, round + 1, teams[0], rotateTeams[round % rotateTeams.size])
            )
            for (i in 1 until teams.size / 2) {
                val home = rotateTeams[(round + i) % rotateTeams.size]
                val away = rotateTeams[(round + rotateTeams.size - i) % rotateTeams.size]
                leagueGames.add(createLeagueGameBy(league, season, round + 1, home, away))
            }
        }

        return leagueGames;
    }

    private fun createLeagueGameBy(league: League, season: Int, round: Int, home: Team, away: Team): LeagueGame {
        val leagueGame = LeagueGame.of(league, season, round)
        leagueGame.addTeam(home, away)
        return leagueGame;
    }
}
