package com.example.soccerdomain.support

import com.example.soccerdomain.player.domain.Position
import com.example.soccerdomain.team.domain.Team
import com.example.soccerdomain.team.domain.formation.Formation
import com.example.soccerdomain.team.domain.formation.FormationElement

class FormationFixture {

    companion object {

        fun create(team: Team, formationName: String): Formation {
            val gk = PlayerFixture.createPlayer(Position.GK, team)
            val rb = PlayerFixture.createPlayer(Position.RB, team)
            val lb = PlayerFixture.createPlayer(Position.LB, team)
            val cb = PlayerFixture.createPlayer(Position.CB, team)
            val cdm = PlayerFixture.createPlayer(Position.CDM, team)
            val rm = PlayerFixture.createPlayer(Position.RM, team)
            val lm = PlayerFixture.createPlayer(Position.LM, team)
            val rw = PlayerFixture.createPlayer(Position.RW, team)
            val lw = PlayerFixture.createPlayer(Position.LW, team)
            val cf = PlayerFixture.createPlayer(Position.CF, team)
            val st = PlayerFixture.createPlayer(Position.ST, team)
            val formation = Formation(formationName = formationName, team = team)
            formation.addFormationElement(
                FormationElement(
                    player = gk,
                    position = gk.positions.first(),
                    formation = formation
                )
            )
            formation.addFormationElement(
                FormationElement(
                    player = rb,
                    position = rb.positions.first(),
                    formation = formation
                )
            )
            formation.addFormationElement(
                FormationElement(
                    player = lb,
                    position = lb.positions.first(),
                    formation = formation
                )
            )
            formation.addFormationElement(
                FormationElement(
                    player = cb,
                    position = cb.positions.first(),
                    formation = formation
                )
            )
            formation.addFormationElement(
                FormationElement(
                    player = cdm,
                    position = cdm.positions.first(),
                    formation = formation
                )
            )

            formation.addFormationElement(
                FormationElement(
                    player = rm,
                    position = rm.positions.first(),
                    formation = formation
                )
            )
            formation.addFormationElement(
                FormationElement(
                    player = lm,
                    position = lm.positions.first(),
                    formation = formation
                )
            )
            formation.addFormationElement(
                FormationElement(
                    player = rw,
                    position = rw.positions.first(),
                    formation = formation
                )
            )
            formation.addFormationElement(
                FormationElement(
                    player = lw,
                    position = lw.positions.first(),
                    formation = formation
                )
            )
            formation.addFormationElement(
                FormationElement(
                    player = cf,
                    position = cf.positions.first(),
                    formation = formation
                )
            )
            formation.addFormationElement(
                FormationElement(
                    player = st,
                    position = st.positions.first(),
                    formation = formation
                )
            )
            return formation
        }
    }
}
