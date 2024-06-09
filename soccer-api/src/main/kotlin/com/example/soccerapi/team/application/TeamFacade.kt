package com.example.soccerapi.team.application

import com.example.soccerapi.team.api.request.ScoutRequest
import com.example.soccerapi.team.api.response.TeamResponse
import com.example.soccerdomain.player.domain.PlayerRepository
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.TeamRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TeamFacade(
    val teamRepository: TeamRepository,
    val playerRepository: PlayerRepository
) {

    fun scout(teamId: Long, request: ScoutRequest) {
        val team = teamRepository.findById(teamId).orElseThrow()
        val players = playerRepository.findPlayerByIdIn(request.playerIds)

        team.scout(players)
    }

    fun getTeam(teamId: Long): TeamResponse {
        return TeamResponse.from(teamRepository.findById(teamId).orElseThrow())
    }

    fun getTeams(league: League): List<TeamResponse> {
        return teamRepository.findTeamByLeague(league).stream()
            .map { TeamResponse.from(it) }
            .toList()
    }
}
