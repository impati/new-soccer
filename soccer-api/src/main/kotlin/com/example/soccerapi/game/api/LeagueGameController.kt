package com.example.soccerapi.game.api

import com.example.soccerapi.game.application.LeagueGameFacade
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LeagueGameController(
    val leagueGameFacade: LeagueGameFacade
) {

    @PostMapping("/league/start")
    fun startLeagueGame() {
        leagueGameFacade.startNewSeason()
    }
}
