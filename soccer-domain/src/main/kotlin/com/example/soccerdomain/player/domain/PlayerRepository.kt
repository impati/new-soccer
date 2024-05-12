package com.example.soccerdomain.player.domain

import org.springframework.data.jpa.repository.JpaRepository


interface PlayerRepository : JpaRepository<Player, Long> {

    fun findPlayerByIdIn(ids: List<Long>): List<Player>
}
