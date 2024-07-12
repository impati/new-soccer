package com.example.soccerapi.game.application

import com.example.soccerdomain.game.LeagueGameFactory
import com.example.soccerdomain.game.LeagueGameRepository
import com.example.soccerdomain.game.season.Season
import com.example.soccerdomain.game.record.TeamRecord
import com.example.soccerdomain.game.record.TeamRecordRepository
import com.example.soccerdomain.game.season.SeasonRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LeagueGameFacade(
    val leagueGameFactory: LeagueGameFactory,
    val leagueGameRepository: LeagueGameRepository,
    val teamRecordRepository: TeamRecordRepository,
    val seasonRepository: SeasonRepository
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

    private fun create(season: Int) {
        val leagueGames = leagueGameFactory.create(season)
        leagueGameRepository.saveAll(leagueGames)
        for (leagueGame in leagueGames) {
            teamRecordRepository.saveAll(leagueGame.teams.map { TeamRecord.of(leagueGame, it) }.toList())
        }
        seasonRepository.save(Season.from(season))
    }
}
