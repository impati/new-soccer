package com.example.soccerdomain.game.record

import com.example.soccerdomain.game.Game
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRecordRepository : JpaRepository<TeamRecord, Long> {


    fun findTeamRecordByGame(game: Game): List<TeamRecord>
}
