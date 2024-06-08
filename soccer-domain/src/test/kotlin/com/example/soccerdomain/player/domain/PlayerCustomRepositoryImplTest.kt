package com.example.soccerdomain.player.domain

import com.example.soccerdomain.SoccerConfig
import com.example.soccerdomain.common.search.NumberOperator
import com.example.soccerdomain.common.search.OrderBy
import com.example.soccerdomain.player.NumberSearchInput
import com.example.soccerdomain.player.PlayerSearchInput
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team
import com.example.soccerdomain.team.domain.TeamRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest


@EnableAutoConfiguration
@SpringBootTest(classes = [SoccerConfig::class])
class PlayerCustomRepositoryImplTest @Autowired constructor(
    val playerRepository: PlayerRepository,
    val teamRepository: TeamRepository
) {

    @AfterEach
    fun tearDown() {
        playerRepository.deleteAll()
        teamRepository.deleteAll()
    }

    @Test
    fun save() {
        val player = Player(
            name = "tester",
            position = setOf(Position.CAM),
            stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
            trait = setOf(Trait.HEADER),
            mainFoot = MainFoot.BOTH
        )

        playerRepository.save(player)

        assertThat(playerRepository.count()).isEqualTo(1)
    }

    @Test
    @DisplayName("선수 이름으로 검색한 경우")
    fun search1() {
        val player = Player(
            name = "tester",
            position = setOf(Position.CAM),
            stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
            trait = setOf(Trait.HEADER),
            mainFoot = MainFoot.BOTH
        )
        playerRepository.save(player)

        val response = playerRepository.search(PlayerSearchInput(name = "tester"))

        assertThat(response).hasSize(1)
    }

    @Test
    @DisplayName("팀 이름으로 검색한 경우")
    fun search2() {
        val player = Player(
            name = "tester",
            position = setOf(Position.CAM),
            stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
            trait = setOf(Trait.HEADER),
            mainFoot = MainFoot.BOTH
        )
        val team = Team(
            league = League.LEAGUE_1,
            name = "teamOne"
        )
        team.hiring(listOf(player))
        teamRepository.save(team)
        playerRepository.save(player)


        val response = playerRepository.search(PlayerSearchInput(teamName = "teamOne"))

        assertThat(response).hasSize(1)
    }

    @Test
    @DisplayName("레이팅으로 검색하기")
    fun search3() {
        playerRepository.save(
            Player(
                name = "tester",
                position = setOf(Position.CAM),
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                trait = setOf(Trait.HEADER),
                mainFoot = MainFoot.BOTH,
                rating = Rating(1000)
            )
        )
        playerRepository.save(
            Player(
                name = "tester",
                position = setOf(Position.CAM),
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                trait = setOf(Trait.HEADER),
                mainFoot = MainFoot.BOTH,
                rating = Rating(1500)
            )
        )
        playerRepository.save(
            Player(
                name = "tester",
                position = setOf(Position.CAM),
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                trait = setOf(Trait.HEADER),
                mainFoot = MainFoot.BOTH,
                rating = Rating(2000)
            )
        )


        val response = playerRepository.search(
            PlayerSearchInput(
                rating = NumberSearchInput(
                    value = 1500,
                    NumberOperator.MORE_THAN_EQUAL,
                    orderBy = OrderBy.ASC
                )
            )
        )

        assertThat(response).hasSize(2)
            .extracting("rating")
            .containsExactly(Rating(1500), Rating(2000))
    }

    @Test
    @DisplayName("주발로 검색하는 경우")
    fun search4() {
        playerRepository.save(
            Player(
                name = "tester",
                position = setOf(Position.CAM),
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                trait = setOf(Trait.HEADER),
                mainFoot = MainFoot.BOTH,
                rating = Rating(1000)
            )
        )

        val response = playerRepository.search(
            PlayerSearchInput(
                mainFoot = listOf(MainFoot.RIGHT, MainFoot.LEFT)
            )
        )

        assertThat(response).isEmpty()
    }

    @Test
    @DisplayName("포지션으로 검색하는 경우")
    fun search5() {
        playerRepository.save(
            Player(
                name = "tester",
                position = setOf(Position.CAM),
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                trait = setOf(Trait.HEADER),
                mainFoot = MainFoot.BOTH,
                rating = Rating(1000)
            )
        )

        val response = playerRepository.search(
            PlayerSearchInput(
                position = Position.CAM
            )
        )

        assertThat(response).hasSize(1)
    }

    @Test
    @DisplayName("특성으로 검색하는 경우")
    fun search6() {
        playerRepository.save(
            Player(
                name = "tester",
                position = setOf(Position.CAM),
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                trait = setOf(Trait.HEADER, Trait.PASS_MASTER),
                mainFoot = MainFoot.BOTH,
                rating = Rating(1000)
            )
        )

        val response = playerRepository.search(
            PlayerSearchInput(
                traits = listOf(Trait.HEADER, Trait.MAJOR)
            )
        )

        assertThat(response).hasSize(1)
    }
}
