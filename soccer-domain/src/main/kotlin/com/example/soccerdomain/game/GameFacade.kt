package com.example.soccerdomain.game

import org.springframework.stereotype.Component

@Component
class GameFacade(
    val gameContextLoader: GameContextLoader,
    val gameExecutor: GameExecutor,
    val gameSaver: GameSaver
) {

    fun runGame(gameParameter: GameParameter): GameResult {
        val gameContext = gameContextLoader.load(game = gameParameter.game, gameParameter.formations)
        val gameResult = gameExecutor.execute(gameContext)
        gameSaver.save(gameResult)

        return gameResult
    }
}
