package com.example.soccerapi.team.api.response

import com.example.soccerapi.player.api.response.PlayerResponse
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team

data class TeamResponse(
    val teamId: Long,
    val name: String,
    val league: League,
    val rating: Int,
    val players: List<PlayerResponse>
) {

    companion object {

        fun from(team: Team): TeamResponse {
            return TeamResponse(
                team.id!!,
                team.name,
                team.league,
                team.rating.value,
                team.player.stream()
                    .map { PlayerResponse.from(it) }
                    .toList())
        }
    }
}
