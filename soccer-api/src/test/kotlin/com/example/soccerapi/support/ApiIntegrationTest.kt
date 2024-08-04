package com.example.soccerapi.support

import com.example.soccerdomain.game.league.LeagueGameRepository
import com.example.soccerdomain.game.record.PlayerRecordRepository
import com.example.soccerdomain.game.record.TeamRecordRepository
import com.example.soccerdomain.game.season.SeasonRepository
import com.example.soccerdomain.player.domain.PlayerRepository
import com.example.soccerdomain.team.domain.TeamRepository
import com.example.soccerdomain.team.domain.formation.FormationRepository
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApiIntegrationTest {

    @Autowired
    lateinit var playerRepository: PlayerRepository

    @Autowired
    lateinit var teamRepository: TeamRepository

    @Autowired
    lateinit var formationRepository: FormationRepository

    @Autowired
    lateinit var playerRecordRepository: PlayerRecordRepository

    @Autowired
    lateinit var teamRecordRepository: TeamRecordRepository

    @Autowired
    lateinit var leagueGameRepository: LeagueGameRepository

    @Autowired
    lateinit var seasonRepository: SeasonRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var integrationHelper: IntegrationHelper
}
