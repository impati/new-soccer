package com.example.soccerdomain.team.domain

import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long> {

    fun findTeamByLeague(league: League): List<Team>
}
