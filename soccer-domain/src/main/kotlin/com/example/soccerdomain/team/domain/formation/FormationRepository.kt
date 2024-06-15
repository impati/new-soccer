package com.example.soccerdomain.team.domain.formation

import com.example.soccerdomain.team.domain.Team
import org.springframework.data.jpa.repository.JpaRepository

interface FormationRepository : JpaRepository<Formation, Long> {

    fun findByTeam(team: Team): List<Formation>
}
