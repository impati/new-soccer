package com.example.soccerapi.game.api

import com.example.soccerapi.game.application.LeagueGameCommandService
import com.example.soccerapi.game.application.LeagueGameQueryService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
class LeagueGameController(
    val leagueGameCommandService: LeagueGameCommandService,
    val leagueGameQueryService: LeagueGameQueryService
) {

    @PostMapping("/league/start")
    fun startLeagueGame() {
        leagueGameCommandService.startNewSeason()
    }

    @GetMapping("/league/seasons/{season}")
    fun getLeagueGames(@PathVariable season: Int): List<LeagueGameResponse> {
        return leagueGameQueryService.getLeagueGames(season)
    }

    @PostMapping("/league/games/{gameId}")
    fun runLeagueGame(@PathVariable gameId: Long, @Valid @RequestBody request: GameRequest): GameResponse {
        return leagueGameCommandService.runGame(gameId, request)
    }
}
