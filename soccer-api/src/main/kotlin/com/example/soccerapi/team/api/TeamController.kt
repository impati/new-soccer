package com.example.soccerapi.team.api

import com.example.soccerapi.team.api.request.FormationRequest
import com.example.soccerapi.team.api.request.ScoutRequest
import com.example.soccerapi.team.api.response.TeamResponse
import com.example.soccerapi.team.application.TeamFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/teams")
class TeamController(
    val teamFacade: TeamFacade
) {

    @PostMapping("/{teamId}/scout")
    fun scout(@PathVariable teamId: Long, @RequestBody scoutRequest: ScoutRequest): ResponseEntity<Any> {
        teamFacade.scout(teamId, scoutRequest)

        return ResponseEntity.ok().build()
    }

    @GetMapping("/{teamId}")
    fun getTeam(@PathVariable teamId: Long): TeamResponse {
        return teamFacade.getTeam(teamId)
    }

    @PostMapping("/{teamId}/formation")
    fun createFormation(@PathVariable teamId: Long, @RequestBody request: FormationRequest) {
        teamFacade.createFormation(teamId, request)
    }
}
