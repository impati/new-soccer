package com.example.soccerdomain.game

import com.example.soccerdomain.game.record.GameRecord

data class GameResult(
    val gameRecords: List<GameRecord>,
    val game: Game
)
