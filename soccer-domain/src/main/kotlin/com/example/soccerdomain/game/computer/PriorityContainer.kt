package com.example.soccerdomain.game.computer

import com.example.soccerdomain.player.domain.Player
import com.example.soccerdomain.player.domain.Position


private val teammatePriority = mapOf(
    Position.CF to mapOf(
        Position.CB to 5,
        Position.LB to 1,
        Position.RB to 1,
        Position.CDM to 6,
        Position.CAM to 14,
        Position.CM to 6,
        Position.LM to 12,
        Position.RM to 12,
        Position.LW to 15,
        Position.RW to 15,
        Position.CF to 0,
        Position.ST to 12,
        Position.GK to 1,
    ),
    Position.GK to mapOf(
        Position.CB to 20,
        Position.LB to 10,
        Position.RB to 10,
        Position.CDM to 15,
        Position.CAM to 5,
        Position.CM to 5,
        Position.LM to 5,
        Position.RM to 5,
        Position.LW to 5,
        Position.RW to 5,
        Position.CF to 5,
        Position.ST to 10,
        Position.GK to 0
    ),
    Position.ST to mapOf(
        Position.CB to 5,
        Position.LB to 1,
        Position.RB to 1,
        Position.CDM to 6,
        Position.CAM to 14,
        Position.CM to 6,
        Position.LM to 12,
        Position.RM to 12,
        Position.LW to 15,
        Position.RW to 15,
        Position.CF to 13,
        Position.ST to 0,
        Position.GK to 1
    ),
    Position.CB to mapOf(
        Position.CB to 0,
        Position.LB to 15,
        Position.RB to 15,
        Position.CDM to 20,
        Position.CAM to 5,
        Position.CM to 5,
        Position.LM to 10,
        Position.RM to 10,
        Position.LW to 5,
        Position.RW to 5,
        Position.CF to 5,
        Position.ST to 5,
        Position.GK to 1
    ),
    Position.LB to mapOf(
        Position.CB to 20,
        Position.LB to 0,
        Position.RB to 10,
        Position.CDM to 10,
        Position.CAM to 5,
        Position.CM to 10,
        Position.LM to 10,
        Position.RM to 5,
        Position.LW to 10,
        Position.RW to 5,
        Position.CF to 10,
        Position.ST to 5,
        Position.GK to 1
    ),
    Position.RB to mapOf(
        Position.CB to 20,
        Position.LB to 10,
        Position.RB to 0,
        Position.CDM to 10,
        Position.CAM to 5,
        Position.CM to 10,
        Position.LM to 5,
        Position.RM to 10,
        Position.LW to 5,
        Position.RW to 10,
        Position.CF to 10,
        Position.ST to 5,
        Position.GK to 1
    ),
    Position.CDM to mapOf(
        Position.CB to 20,
        Position.LB to 10,
        Position.RB to 10,
        Position.CDM to 0,
        Position.CAM to 10,
        Position.CM to 10,
        Position.LM to 10,
        Position.RM to 10,
        Position.LW to 5,
        Position.RW to 5,
        Position.CF to 5,
        Position.ST to 5,
        Position.GK to 1
    ),
    Position.CAM to mapOf(
        Position.CB to 5,
        Position.LB to 5,
        Position.RB to 5,
        Position.CDM to 10,
        Position.CAM to 0,
        Position.CM to 10,
        Position.LM to 10,
        Position.RM to 10,
        Position.LW to 10,
        Position.RW to 10,
        Position.CF to 15,
        Position.ST to 10,
        Position.GK to 1
    ),
    Position.CM to mapOf(
        Position.CB to 10,
        Position.LB to 10,
        Position.RB to 10,
        Position.CDM to 10,
        Position.CAM to 10,
        Position.CM to 0,
        Position.LM to 10,
        Position.RM to 10,
        Position.LW to 5,
        Position.RW to 5,
        Position.CF to 5,
        Position.ST to 5,
        Position.GK to 1
    ),
    Position.LM to mapOf(
        Position.CB to 10,
        Position.LB to 15,
        Position.RB to 5,
        Position.CDM to 5,
        Position.CAM to 10,
        Position.CM to 10,
        Position.LM to 0,
        Position.RM to 10,
        Position.LW to 15,
        Position.RW to 5,
        Position.CF to 10,
        Position.ST to 5,
        Position.GK to 1
    ),
    Position.RM to mapOf(
        Position.CB to 10,
        Position.LB to 5,
        Position.RB to 15,
        Position.CDM to 5,
        Position.CAM to 10,
        Position.CM to 10,
        Position.LM to 10,
        Position.RM to 0,
        Position.LW to 5,
        Position.RW to 15,
        Position.CF to 10,
        Position.ST to 5,
        Position.GK to 1
    ),
    Position.LW to mapOf(
        Position.CB to 10,
        Position.LB to 10,
        Position.RB to 5,
        Position.CDM to 5,
        Position.CAM to 10,
        Position.CM to 5,
        Position.LM to 15,
        Position.RM to 5,
        Position.LW to 0,
        Position.RW to 10,
        Position.CF to 15,
        Position.ST to 10,
        Position.GK to 1
    ),
    Position.RW to mapOf(
        Position.CB to 10,
        Position.LB to 5,
        Position.RB to 10,
        Position.CDM to 5,
        Position.CAM to 10,
        Position.CM to 5,
        Position.LM to 5,
        Position.RM to 15,
        Position.LW to 10,
        Position.RW to 0,
        Position.CF to 15,
        Position.ST to 10,
        Position.GK to 1
    ),
)

private val opponentPriority = mapOf(
    Position.CF to mapOf(
        Position.CB to 18,
        Position.LB to 8,
        Position.RB to 8,
        Position.CDM to 18,
        Position.CAM to 18,
        Position.CM to 18,
        Position.LM to 4,
        Position.RM to 5,
        Position.LW to 1,
        Position.RW to 1,
        Position.CF to 1,
        Position.ST to 1,
        Position.GK to 1,
    ),
    Position.GK to mapOf(
        Position.CB to 10, // 9 증가
        Position.LB to 10, // 9 증가
        Position.RB to 10, // 9 증가
        Position.CDM to 10, // 9 증가
        Position.CAM to 10, // 9 증가
        Position.CM to 10, // 9 증가
        Position.LM to 5, // 4 증가
        Position.RM to 5, // 4 증가
        Position.LW to 5, // 4 증가
        Position.RW to 5, // 4 증가
        Position.CF to 5, // 4 증가
        Position.ST to 5, // 4 증가
        Position.GK to 1
    ),
    Position.ST to mapOf(
        Position.CB to 5,
        Position.LB to 1,
        Position.RB to 1,
        Position.CDM to 6,
        Position.CAM to 14,
        Position.CM to 6,
        Position.LM to 12,
        Position.RM to 12,
        Position.LW to 15,
        Position.RW to 15,
        Position.CF to 13,
        Position.ST to 1,
        Position.GK to 1
    ),
    Position.CB to mapOf(
        Position.CB to 10, // 9 증가
        Position.LB to 10, // 9 증가
        Position.RB to 10, // 9 증가
        Position.CDM to 10, // 9 증가
        Position.CAM to 10, // 9 증가
        Position.CM to 10, // 9 증가
        Position.LM to 5, // 4 증가
        Position.RM to 5, // 4 증가
        Position.LW to 5, // 4 증가
        Position.RW to 5, // 4 증가
        Position.CF to 5, // 4 증가
        Position.ST to 5, // 4 증가
        Position.GK to 1
    ),
    Position.LB to mapOf(
        Position.CB to 10, // 9 증가
        Position.LB to 10, // 9 증가
        Position.RB to 10, // 9 증가
        Position.CDM to 10, // 9 증가
        Position.CAM to 10, // 9 증가
        Position.CM to 10, // 9 증가
        Position.LM to 5, // 4 증가
        Position.RM to 5, // 4 증가
        Position.LW to 5, // 4 증가
        Position.RW to 5, // 4 증가
        Position.CF to 5, // 4 증가
        Position.ST to 5, // 4 증가
        Position.GK to 1
    ),
    Position.RB to mapOf(
        Position.CB to 10, // 9 증가
        Position.LB to 10, // 9 증가
        Position.RB to 10, // 9 증가
        Position.CDM to 10, // 9 증가
        Position.CAM to 10, // 9 증가
        Position.CM to 10, // 9 증가
        Position.LM to 5, // 4 증가
        Position.RM to 5, // 4 증가
        Position.LW to 5, // 4 증가
        Position.RW to 5, // 4 증가
        Position.CF to 5, // 4 증가
        Position.ST to 5, // 4 증가
        Position.GK to 1
    ),
    Position.CDM to mapOf(
        Position.CB to 10, // 9 증가
        Position.LB to 10, // 9 증가
        Position.RB to 10, // 9 증가
        Position.CDM to 10, // 9 증가
        Position.CAM to 10, // 9 증가
        Position.CM to 10, // 9 증가
        Position.LM to 5, // 4 증가
        Position.RM to 5, // 4 증가
        Position.LW to 5, // 4 증가
        Position.RW to 5, // 4 증가
        Position.CF to 5, // 4 증가
        Position.ST to 5, // 4 증가
        Position.GK to 1
    ),
    Position.CAM to mapOf(
        Position.CB to 10, // 9 증가
        Position.LB to 10, // 9 증가
        Position.RB to 10, // 9 증가
        Position.CDM to 10, // 9 증가
        Position.CAM to 10, // 9 증가
        Position.CM to 10, // 9 증가
        Position.LM to 5, // 4 증가
        Position.RM to 5, // 4 증가
        Position.LW to 5, // 4 증가
        Position.RW to 5, // 4 증가
        Position.CF to 5, // 4 증가
        Position.ST to 5, // 4 증가
        Position.GK to 1
    ),
    Position.CM to mapOf(
        Position.CB to 10, // 9 증가
        Position.LB to 10, // 9 증가
        Position.RB to 10, // 9 증가
        Position.CDM to 10, // 9 증가
        Position.CAM to 10, // 9 증가
        Position.CM to 10, // 9 증가
        Position.LM to 5, // 4 증가
        Position.RM to 5, // 4 증가
        Position.LW to 5, // 4 증가
        Position.RW to 5, // 4 증가
        Position.CF to 5, // 4 증가
        Position.ST to 5, // 4 증가
        Position.GK to 1
    ),
    Position.LM to mapOf(
        Position.CB to 10, // 9 증가
        Position.LB to 10, // 9 증가
        Position.RB to 10, // 9 증가
        Position.CDM to 10, // 9 증가
        Position.CAM to 10, // 9 증가
        Position.CM to 10, // 9 증가
        Position.LM to 5, // 4 증가
        Position.RM to 5, // 4 증가
        Position.LW to 5, // 4 증가
        Position.RW to 5, // 4 증가
        Position.CF to 5, // 4 증가
        Position.ST to 5, // 4 증가
        Position.GK to 1
    ),
    Position.RM to mapOf(
        Position.CB to 10, // 9 증가
        Position.LB to 10, // 9 증가
        Position.RB to 10, // 9 증가
        Position.CDM to 10, // 9 증가
        Position.CAM to 10, // 9 증가
        Position.CM to 10, // 9 증가
        Position.LM to 5, // 4 증가
        Position.RM to 5, // 4 증가
        Position.LW to 5, // 4 증가
        Position.RW to 5, // 4 증가
        Position.CF to 5, // 4 증가
        Position.ST to 5, // 4 증가
        Position.GK to 1
    ),
    Position.LW to mapOf(
        Position.CB to 10, // 9 증가
        Position.LB to 10, // 9 증가
        Position.RB to 10, // 9 증가
        Position.CDM to 10, // 9 증가
        Position.CAM to 10, // 9 증가
        Position.CM to 10, // 9 증가
        Position.LM to 5, // 4 증가
        Position.RM to 5, // 4 증가
        Position.LW to 5, // 4 증가
        Position.RW to 5, // 4 증가
        Position.CF to 5, // 4 증가
        Position.ST to 5, // 4 증가
        Position.GK to 1
    ),
    Position.RW to mapOf(
        Position.CB to 10, // 9 증가
        Position.LB to 10, // 9 증가
        Position.RB to 10, // 9 증가
        Position.CDM to 10, // 9 증가
        Position.CAM to 10, // 9 증가
        Position.CM to 10, // 9 증가
        Position.LM to 5, // 4 증가
        Position.RM to 5, // 4 증가
        Position.LW to 5, // 4 증가
        Position.RW to 5, // 4 증가
        Position.CF to 5, // 4 증가
        Position.ST to 5, // 4 증가
        Position.GK to 1
    )
)

fun getTeammatePriority(player: Player): Map<Position, Int> {
    return teammatePriority[player.position]!!
}

fun getOpponentPriority(player: Player): Map<Position, Int> {
    return opponentPriority[player.position]!!
}

