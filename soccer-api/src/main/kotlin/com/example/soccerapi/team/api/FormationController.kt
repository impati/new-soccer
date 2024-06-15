package com.example.soccerapi.team.api

import com.example.soccerapi.team.api.request.FormationRequest
import com.example.soccerapi.team.api.response.FormationResponse
import com.example.soccerapi.team.application.TeamFacade
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class FormationController(
    val teamFacade: TeamFacade
) {


    @PostMapping("/teams/{teamId}/formations")
    fun createFormation(@PathVariable teamId: Long, @RequestBody request: FormationRequest): FormationResponse {
        return teamFacade.createFormation(teamId, request)
    }

    @GetMapping("/teams/{teamId}/formations")
    fun getFormation(@PathVariable teamId: Long): List<FormationResponse> {
        return teamFacade.getFormations(teamId)
    }

    @PutMapping("/teams/{teamId}/formations/{formationId}")
    fun editFormation(
        @PathVariable teamId: Long,
        @PathVariable formationId: Long,
        @RequestBody request: FormationRequest
    ): FormationResponse {
        return teamFacade.editFormation(formationId, request)
    }

    @DeleteMapping("/teams/{teamId}/formations/{formationId}")
    fun deleteFormation(@PathVariable teamId: Long, @PathVariable formationId: Long) {
        teamFacade.deleteFormation(formationId)
    }
}
