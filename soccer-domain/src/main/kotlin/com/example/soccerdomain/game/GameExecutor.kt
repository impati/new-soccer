package com.example.soccerdomain.game

import com.example.soccerdomain.game.computer.GameComputer
import com.example.soccerdomain.game.record.GameRecord
import com.example.soccerdomain.game.record.GameRecords
import com.example.soccerdomain.game.record.PlayerRecord
import com.example.soccerdomain.game.record.TeamRecord
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class GameExecutor(
    val computer: GameComputer
) {

    fun execute(gameContext: GameContext): GameResult {
        gameContext.game.start()

        val gameRecords = running(gameContext)

        gameContext.game.finish()
        return GameResult(gameRecords, gameContext.game)
    }

    private fun running(gameContext: GameContext): List<GameRecord> {
        val game = gameContext.game
        val gameRecords = GameRecords(gameContext.participates.map {
            val teamRecord = TeamRecord.of(game, it.team)
            GameRecord(
                teamRecord,
                it.players.map { player -> PlayerRecord.of(game, teamRecord, player, player.position!!) }
            )
        })
        for (gameRecord in gameRecords.gameRecords) {
            gameRecord.playerRecords.forEach { playerRecord ->
                val teammate = gameRecords.getTeamMate(playerRecord.player.team!!)
                val opponent = gameRecords.getOpponent(playerRecord.player.team!!)
                computer.compute(playerRecord, teammate, opponent)
            }
        }
        for (gameRecord in gameRecords.gameRecords) {
            computer.compute(
                gameRecord.teamRecord,
                gameRecords.getTeamMate(gameRecord.teamRecord.team),
                gameRecords.getOpponent(gameRecord.teamRecord.team)
            )
        }
        return gameRecords.gameRecords;
    }
}
