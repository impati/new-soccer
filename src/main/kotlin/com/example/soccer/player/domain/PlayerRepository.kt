package com.example.soccer.player.domain

import org.springframework.data.jpa.repository.JpaRepository

interface PlayerRepository : JpaRepository<Player,Long>{
}
