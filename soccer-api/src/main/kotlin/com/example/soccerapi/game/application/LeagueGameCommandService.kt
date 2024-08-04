package com.example.soccerapi.game.application

import com.example.soccerapi.game.api.request.GameRequest
import com.example.soccerapi.game.api.response.GameResultResponse
import com.example.soccerdomain.game.GameFacade
import com.example.soccerdomain.game.GameParameter
import com.example.soccerdomain.game.GameRepository
import com.example.soccerdomain.game.league.LeagueGameFactory
import com.example.soccerdomain.game.league.LeagueGameRepository
import com.example.soccerdomain.game.season.Season
import com.example.soccerdomain.game.season.SeasonRepository
import com.example.soccerdomain.team.domain.formation.FormationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LeagueGameCommandService(
    val leagueGameFactory: LeagueGameFactory,
    val leagueGameRepository: LeagueGameRepository,
    val seasonRepository: SeasonRepository,
    val gameRepository: GameRepository,
    val formationRepository: FormationRepository,
    val gameFacade: GameFacade
) {

    fun startNewSeason() {
        val season = seasonRepository.findLastSeason()
        if (season.isEmpty) {
            create(Season.START_SEASON)
            return
        }
        if (season.get().isPossibleNextSeason()) {
            create(season.get().nextSeason())
            return
        }

        throw IllegalStateException("새로운 시즌을 시작하기 위해서는 시즌이 완료 상태여야합니다.")
    }

    fun runGame(gameId: Long, request: GameRequest): GameResultResponse {
        val leagueGame = gameRepository.findById(gameId).orElseThrow()
        val formations = formationRepository.findAllById(request.formationIds)
        val gameResult = gameFacade.runGame(GameParameter(leagueGame, formations))
        return GameResultResponse.from(gameResult)
    }

    private fun create(season: Int) {
        val leagueGames = leagueGameFactory.create(season)
        leagueGameRepository.saveAll(leagueGames)
        seasonRepository.save(Season.from(season))
    }

}
