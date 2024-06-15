package com.example.soccerapi.team.application

import com.example.soccerapi.team.api.request.FormationRequest
import com.example.soccerapi.team.api.request.ScoutRequest
import com.example.soccerapi.team.api.response.FormationResponse
import com.example.soccerapi.team.api.response.TeamResponse
import com.example.soccerdomain.player.domain.PlayerRepository
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.TeamRepository
import com.example.soccerdomain.team.domain.formation.Formation
import com.example.soccerdomain.team.domain.formation.FormationElement
import com.example.soccerdomain.team.domain.formation.FormationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TeamFacade(
    val formationRepository: FormationRepository,
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

    fun createFormation(teamId: Long, request: FormationRequest): FormationResponse {
        val team = teamRepository.findById(teamId).orElseThrow()
        val formation = Formation(request.name, team)

        for (element in request.elements) {
            val player = playerRepository.findById(element.playerId).orElseThrow()
            formation.addFormationElement(FormationElement(element.position, player, formation))
        }

        return FormationResponse.from(formationRepository.save(formation))
    }

    fun getFormations(teamId: Long): List<FormationResponse> {
        val team = teamRepository.findById(teamId).orElseThrow()

        return formationRepository.findByTeam(team).map { FormationResponse.from(it) }
    }

    fun editFormation(formationId: Long, request: FormationRequest): FormationResponse {
        val formation = formationRepository.findById(formationId).orElseThrow()

        formation.editFormationName(request.name)
        formation.clearFormationElement()

        for (element in request.elements) {
            val player = playerRepository.findById(element.playerId).orElseThrow()
            formation.addFormationElement(FormationElement(element.position, player, formation))
        }

        return FormationResponse.from(formationRepository.save(formation))
    }

    fun deleteFormation(formationId: Long) {
        formationRepository.deleteById(formationId)
    }
}
