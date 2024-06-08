package com.example.soccerapi.team.api.response

import com.example.soccerapi.player.api.response.PlayerResponse
import com.example.soccerdomain.team.domain.Team

data class TeamResponse(
    val teamId: Long,
    val name: String,
    val league: LeagueResponse,
    val rating: Int,
    val players: List<PlayerResponse>
) {

    companion object {

        fun from(team: Team): TeamResponse {
            return TeamResponse(
                team.id!!,
                team.name,
                LeagueResponse(team.league.name, team.league.leagueName),
                team.rating.value,
                team.player.stream()
                    .map { PlayerResponse.from(it) }
                    .toList())
        }
    }
}
