package com.example.soccerdomain.game.record

data class GameRecord(
    val teamRecord: TeamRecord,
    val playerRecords: List<PlayerRecord>
)
