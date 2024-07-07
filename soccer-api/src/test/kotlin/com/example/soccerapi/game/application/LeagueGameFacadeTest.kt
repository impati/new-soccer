package com.example.soccerapi.game.application

import com.example.soccerdomain.game.LeagueGameRepository
import com.example.soccerdomain.game.record.TeamRecordRepository
import com.example.soccerdomain.game.season.SeasonRepository
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team
import com.example.soccerdomain.team.domain.TeamRepository
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class LeagueGameFacadeTest @Autowired constructor(
    val leagueGameFacade: LeagueGameFacade,
    val leagueGameRepository: LeagueGameRepository,
    val teamRecordRepository: TeamRecordRepository,
    val teamRepository: TeamRepository,
    val seasonRepository: SeasonRepository,
    val entityManager: EntityManager
) {


    @Test
    @DisplayName("1시즌을 시작한다.")
    fun startNewSeason1() {
        teamRepository.save(Team(league = League.LEAGUE_1, name = "A"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "B"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "C"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "D"))

        leagueGameFacade.startNewSeason()

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
        leagueGameFacade.startNewSeason()
        val season = seasonRepository.findLastSeason().orElseThrow()
        season.doneSeason()
        entityManager.flush()
        entityManager.clear()

        leagueGameFacade.startNewSeason()

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
        leagueGameFacade.startNewSeason()
        entityManager.flush()
        entityManager.clear()

        assertThatCode { (leagueGameFacade.startNewSeason()) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("새로운 시즌을 시작하기 위해서는 시즌이 완료 상태여야합니다.")
    }
}
