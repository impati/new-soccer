package com.example.soccerapi.game.api.response

import com.example.soccerapi.team.api.response.TeamResponse
import com.example.soccerdomain.game.GameStatus
import com.example.soccerdomain.game.league.LeagueGame
import com.example.soccerdomain.team.domain.League

data class LeagueGameResponse(
    val league: League,
    val season: Int,
    val round: Int,
    val status: GameStatus,
    val teamResponse: List<TeamResponse>,
    val gameId: Long
) {

    companion object {
        fun from(leagueGame: LeagueGame): LeagueGameResponse {
            return LeagueGameResponse(
                league = leagueGame.league,
                season = leagueGame.season,
                round = leagueGame.round,
                status = leagueGame.status,
                teamResponse = leagueGame.teams.map { TeamResponse.from(it.team) },
                gameId = leagueGame.id!!
            )
        }
    }
}
