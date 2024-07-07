package com.example.soccerdomain.game

import com.example.soccerdomain.SoccerConfig
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team
import com.example.soccerdomain.team.domain.TeamRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest


@EnableAutoConfiguration
@SpringBootTest(classes = [SoccerConfig::class])
class LeagueGameFactoryTest @Autowired constructor(
    val teamRepository: TeamRepository,
    val leagueGameFactory: LeagueGameFactory
) {

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

        assertThat(leagueGames).hasSize((teamSize - 1) * (teamSize / 2))
        leagueGames
            .flatMap { it.teams }
            .groupingBy { it }.eachCount().mapValues { it.value }
            .values
            .forEach { assertThat(it).isEqualTo(teamSize - 1) }
    }
}
