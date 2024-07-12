package com.example.soccerdomain.game.league

import com.example.soccerdomain.support.DomainIntegrationTest
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class LeagueGameFactoryTest @Autowired constructor(
    val leagueGameFactory: LeagueGameFactory
) : DomainIntegrationTest() {


    @Test
    @DisplayName("팀들이 서로 한 번씩 경기를 한다.")
    fun create() {
        val teamSize = 8;
        teamRepository.save(Team(league = League.LEAGUE_1, name = "A"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "B"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "C"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "D"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "E"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "F"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "G"))
        teamRepository.save(Team(league = League.LEAGUE_1, name = "H"))
        val season = 1

        val leagueGames = leagueGameFactory.create(season)

        Assertions.assertThat(leagueGames).hasSize((teamSize - 1) * (teamSize / 2))
        leagueGames
            .flatMap { it.teams }
            .groupingBy { it }.eachCount().mapValues { it.value }
            .values
            .forEach { Assertions.assertThat(it).isEqualTo(teamSize - 1) }
    }
}
