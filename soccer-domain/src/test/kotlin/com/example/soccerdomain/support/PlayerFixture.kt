package com.example.soccerdomain.support

import com.example.soccerdomain.player.domain.*
import com.example.soccerdomain.team.domain.Team

class PlayerFixture {

    companion object {
        fun createStrikerPlayer(): Player {
            return Player(
                name = "striker",
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                mainFoot = MainFoot.BOTH,
                positions = setOf(Position.ST),
                traits = setOf(Trait.KICK)
            )
        }

        fun createPlayer(position: Position): Player {
            return Player(
                name = "default$position",
                stat = getStat(),
                mainFoot = MainFoot.BOTH,
                positions = setOf(position),
                traits = setOf(Trait.KICK)
            )
        }

        fun createPlayer(position: Position, team: Team): Player {
            return Player(
                name = "default$position",
                stat = getStat(),
                mainFoot = MainFoot.BOTH,
                positions = setOf(position),
                traits = setOf(Trait.KICK),
                team = team
            )
        }

        fun createGoalKeeperPlayer(): Player {
            return Player(
                name = "goalKeeper",
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                mainFoot = MainFoot.LEFT,
                positions = setOf(Position.GK),
                traits = setOf(Trait.WALL)
            )
        }

        private fun getStat(): Stat {
            return Stat(
                Basic(50, 50, 50),
                Physical(50, 50, 50, 50, 50, 50, 50),
                Pass(50, 50, 50, 50),
                Forward(50, 50, 50, 50, 50),
                Defense(50, 50, 50, 50),
                GoalKeeping(50, 50, 50, 50)
            )
        }
    }
}
