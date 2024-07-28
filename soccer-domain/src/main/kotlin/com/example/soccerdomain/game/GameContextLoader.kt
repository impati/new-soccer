package com.example.soccerdomain.game

import com.example.soccerdomain.team.domain.formation.Formation
import org.springframework.stereotype.Component

@Component
class GameContextLoader {

    fun load(game: Game, formations: List<Formation>): GameContext {
        return GameContext(
            participates = listOf(getParticipate(game, formations[0]), getParticipate(game, formations[1])),
            game = game
        )
    }

    private fun getParticipate(game: Game, formation: Formation): Participate {
        val team = game.teams.first { it.team == formation.team }.team

        for (element in formation.formationElements) {
            element.player.setParticipate(element.position)
        }

        return Participate(team, formation.formationElements.map { it.player }.toList())
    }
}
