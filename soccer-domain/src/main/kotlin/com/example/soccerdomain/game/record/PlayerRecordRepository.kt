package com.example.soccerdomain.game.record

import org.springframework.data.jpa.repository.JpaRepository

interface PlayerRecordRepository : JpaRepository<PlayerRecord, Long> {

    fun findPlayerRecordByTeamRecord(teamRecord: TeamRecord): List<PlayerRecord>
}
