package com.example.soccerapi.team.api

import com.example.soccerapi.team.api.response.LeagueResponse
import com.example.soccerapi.team.api.response.TeamResponse
import com.example.soccerapi.team.application.TeamFacade
import com.example.soccerdomain.team.domain.League
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/leagues")
class LeagueController(
    val teamFacade: TeamFacade
) {

    @GetMapping("/{league}")
    fun getTeams(@PathVariable league: League): List<TeamResponse> = teamFacade.getTeams(league)

    @GetMapping
    fun getLeagues(): List<LeagueResponse> = League.entries.map { LeagueResponse(it.name, it.leagueName) }
}
