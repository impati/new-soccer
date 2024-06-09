package com.example.soccerdomain.team.domain.formation

import org.springframework.data.jpa.repository.JpaRepository

interface FormationElementRepository : JpaRepository<FormationElement, Long> {

    fun findByFormation(formation: Formation): List<FormationElement>
}
