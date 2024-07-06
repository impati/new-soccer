package com.example.soccerdomain.game.record

import org.springframework.data.jpa.repository.JpaRepository

interface TeamRecordRepository : JpaRepository<TeamRecord, Long> {
}
