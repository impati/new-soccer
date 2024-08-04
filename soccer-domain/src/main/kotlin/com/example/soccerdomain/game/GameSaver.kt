package com.example.soccerdomain.game

import com.example.soccerdomain.game.record.GameRecord
import com.example.soccerdomain.game.record.PlayerRecordRepository
import com.example.soccerdomain.game.record.TeamRecordRepository
import org.springframework.stereotype.Component


@Component
class GameSaver(
    private val gameRepository: GameRepository,
    private val teamRecordRepository: TeamRecordRepository,
    private val playerRecordRepository: PlayerRecordRepository
) {

    fun save(gameResult: GameResult) {
        gameRepository.save(gameResult.game)
        saveRecord(gameResult.gameRecords[0])
        saveRecord(gameResult.gameRecords[1])
    }

    private fun saveRecord(gameRecord: GameRecord) {
        teamRecordRepository.save(gameRecord.teamRecord)
        playerRecordRepository.saveAll(gameRecord.playerRecords)
    }
}
