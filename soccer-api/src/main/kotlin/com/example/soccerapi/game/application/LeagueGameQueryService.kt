package com.example.soccerapi.game.application

import com.example.soccerapi.game.api.LeagueGameResponse
import com.example.soccerdomain.game.league.LeagueGameRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class LeagueGameQueryService(
    val leagueGameRepository: LeagueGameRepository
) {


    fun getLeagueGames(season: Int): List<LeagueGameResponse> {
        return leagueGameRepository.findLeagueGameBySeason(season).map { LeagueGameResponse.from(it) }
    }
}
