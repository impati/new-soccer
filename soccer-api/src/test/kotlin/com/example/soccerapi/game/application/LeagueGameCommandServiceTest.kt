package com.example.soccerapi.game.application

import com.example.soccerapi.game.api.request.GameRequest
import com.example.soccerapi.support.ApiIntegrationTest
import com.example.soccerdomain.game.season.Season
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class LeagueGameCommandServiceTest @Autowired constructor(
    val leagueGameCommandService: LeagueGameCommandService,
    val leagueGameQueryService: LeagueGameQueryService
) : ApiIntegrationTest() {

    @Test
    @DisplayName("1시즌을 시작한다.")
    fun startNewSeason1() {
        teamRepository.save(Team(league = League.LEAGUE_1, name = "A"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "B"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "C"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "D"))

        leagueGameCommandService.startNewSeason()

        val season = seasonRepository.findLastSeason().orElseThrow()
        val leagueGames = leagueGameRepository.findLeagueGameBySeason(season.value)
        assertThat(leagueGames).hasSize(3 * 2)
        leagueGames.stream().forEach { assertThat(teamRecordRepository.findTeamRecordByGame(it)).hasSize(2) }
        assertThat(season.value).isEqualTo(1)
    }

    @Test
    @DisplayName("2시즌을 시작한다.")
    fun startNewSeason2() {
        teamRepository.save(Team(league = League.LEAGUE_1, name = "A"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "B"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "C"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "D"))
        leagueGameCommandService.startNewSeason()
        val season = seasonRepository.findLastSeason().orElseThrow()
        season.doneSeason()
        entityManager.flush()
        entityManager.clear()

        leagueGameCommandService.startNewSeason()

        val season2 = seasonRepository.findLastSeason().orElseThrow()
        val leagueGames = leagueGameRepository.findLeagueGameBySeason(season2.value)
        assertThat(leagueGames).hasSize(3 * 2)
        leagueGames.stream().forEach { assertThat(teamRecordRepository.findTeamRecordByGame(it)).hasSize(2) }
        assertThat(season2.value).isEqualTo(2)
    }

    @Test
    @DisplayName("시즌이 끝나지 않았는데 새로운 시즌을 시작하려고하면 예외가 발생한다.")
    fun startNewSeasonFail() {
        teamRepository.save(Team(league = League.LEAGUE_1, name = "A"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "B"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "C"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "D"))
        leagueGameCommandService.startNewSeason()
        entityManager.flush()
        entityManager.clear()

        Assertions.assertThatCode { (leagueGameCommandService.startNewSeason()) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("새로운 시즌을 시작하기 위해서는 시즌이 완료 상태여야합니다.")
    }

    @Test
    @DisplayName("게임을 실행한다.")
    fun runGame() {
        val teamA = integrationHelper.createTeamWithPlayers()
        val teamB = integrationHelper.createTeamWithPlayers()
        leagueGameCommandService.startNewSeason()
        val leagueGames = leagueGameQueryService.getLeagueGames(Season.START_SEASON)
        val formationA = formationRepository.findByTeam(teamA).first()
        val formationB = formationRepository.findByTeam(teamB).first()
        val gameRequest = GameRequest(listOf(formationA.id!!, formationB.id!!))

        val response = leagueGameCommandService.runGame(leagueGames[0].gameId, gameRequest)

        assertThat(response).isNotNull
        assertThat(response.game.id).isEqualTo(leagueGames.first().gameId)
        println(response)
    }
}

