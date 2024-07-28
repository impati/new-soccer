package com.example.soccerdomain.game.computer.stat

import com.example.soccerdomain.game.Game
import com.example.soccerdomain.game.computer.Opponent
import com.example.soccerdomain.game.computer.Teammate
import com.example.soccerdomain.game.record.PlayerRecord
import com.example.soccerdomain.game.record.TeamRecord
import com.example.soccerdomain.player.domain.*
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team

class StatMemory(
    val playerRecord: PlayerRecord,
    val teammate: Teammate,
    val opponent: Opponent
)

class StatComputerHelper {

    companion object {

        fun highCase(position: Position): StatMemory {
            val game = Game()
            val teamA = team("teamA")
            val teamB = team("teamB")
            val teamRecordA = TeamRecord.of(game, teamA)
            val teamRecordB = TeamRecord.of(game, teamB)
            val playerRecord = PlayerRecord(
                position = position,
                game = game,
                player = player(teamA, 90, position),
                teamRecord = teamRecordA
            )
            val teammate = Teammate(getPlayerRecords(game, teamA, teamRecordA, 90))
            val opponent = Opponent(getPlayerRecords(game, teamB, teamRecordB, 90))
            return StatMemory(playerRecord, teammate, opponent)
        }

        fun midCase(position: Position): StatMemory {
            val game = Game()
            val teamA = team("teamA")
            val teamB = team("teamB")
            val teamRecordA = TeamRecord.of(game, teamA)
            val teamRecordB = TeamRecord.of(game, teamB)
            val playerRecord = PlayerRecord(
                position = position,
                game = game,
                player = player(teamA, 50, position),
                teamRecord = teamRecordA
            )
            val teammate = Teammate(getPlayerRecords(game, teamA, teamRecordA, 50))
            val opponent = Opponent(getPlayerRecords(game, teamB, teamRecordB, 50))
            return StatMemory(playerRecord, teammate, opponent)
        }

        fun lowCase(position: Position): StatMemory {
            val game = Game()
            val teamA = team("teamA")
            val teamB = team("teamB")
            val teamRecordA = TeamRecord.of(game, teamA)
            val teamRecordB = TeamRecord.of(game, teamB)
            val playerRecord = PlayerRecord(
                position = position,
                game = game,
                player = player(teamA, 30, position),
                teamRecord = teamRecordA
            )
            val teammate = Teammate(getPlayerRecords(game, teamA, teamRecordA, 30))
            val opponent = Opponent(getPlayerRecords(game, teamB, teamRecordB, 30))
            return StatMemory(playerRecord, teammate, opponent)
        }

        private fun getPlayerRecords(
            game: Game,
            team: Team,
            teamRecord: TeamRecord,
            avg: Int
        ) =
            listOf(
                PlayerRecord(
                    position = Position.RW,
                    game = game,
                    player = player(team, avg, Position.RW),
                    teamRecord = teamRecord
                ),
                PlayerRecord(
                    position = Position.LW,
                    game = game,
                    player = player(team, avg, Position.LW),
                    teamRecord = teamRecord
                ),
                PlayerRecord(
                    position = Position.CM,
                    game = game,
                    player = player(team, avg, Position.CM),
                    teamRecord = teamRecord
                ),
                PlayerRecord(
                    position = Position.RM,
                    game = game,
                    player = player(team, avg, Position.RM),
                    teamRecord = teamRecord
                ),
                PlayerRecord(
                    position = Position.LM,
                    game = game,
                    player = player(team, avg, Position.LM),
                    teamRecord = teamRecord
                ),
                PlayerRecord(
                    position = Position.CDM,
                    game = game,
                    player = player(team, avg, Position.CDM),
                    teamRecord = teamRecord
                ),
                PlayerRecord(
                    position = Position.RB,
                    game = game,
                    player = player(team, avg, Position.RB),
                    teamRecord = teamRecord
                ),
                PlayerRecord(
                    position = Position.LB,
                    game = game,
                    player = player(team, avg, Position.LB),
                    teamRecord = teamRecord
                ),
                PlayerRecord(
                    position = Position.CB,
                    game = game,
                    player = player(team, avg, Position.CB),
                    teamRecord = teamRecord
                ),
                PlayerRecord(
                    position = Position.GK,
                    game = game,
                    player = player(team, avg, Position.GK),
                    teamRecord = teamRecord
                ),
            )


        private fun team(name: String): Team {
            return Team(name = name, league = League.LEAGUE_1)
        }

        private fun player(team: Team, avg: Int, position: Position): Player {
            return Player(
                name = position.name,
                team = team,
                position = position,
                stat = getStat(avg),
                positions = setOf(position),
                mainFoot = MainFoot.RIGHT,
                traits = setOf()
            )
        }

        private fun getStat(avg: Int): Stat {
            return Stat(
                basic = Basic(
                    positioning = avg,
                    visualRange = avg,
                    sense = avg
                ),
                physical = Physical(
                    physicalFight = avg,
                    acceleration = avg,
                    activeness = avg,
                    balance = avg,
                    speed = avg,
                    stamina = avg,
                    jump = avg
                ),
                pass = Pass(
                    pass = avg,
                    longPass = avg,
                    crosses = avg,
                    ballControl = avg
                ),
                forward = Forward(
                    dribble = avg,
                    goalDetermination = avg,
                    midRangeShot = avg,
                    shootPower = avg,
                    heading = avg
                ),
                defense = Defense(
                    intercepting = avg,
                    tackle = avg,
                    slidingTackle = avg,
                    defense = avg
                ),
                goalKeeping = GoalKeeping(
                    diving = avg,
                    handling = avg,
                    goalKick = avg,
                    speedReaction = avg,
                )
            )
        }
    }
}
