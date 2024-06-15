package com.example.soccerapi.team.api.response

import com.example.soccerdomain.player.domain.Position
import com.example.soccerdomain.team.domain.formation.FormationElement

data class FormationElementResponse(

    val playerId: Long,
    val playerName: String,
    val position: Position
) {


    companion object {

        fun from(formationElement: FormationElement): FormationElementResponse {
            return FormationElementResponse(
                formationElement.player.id!!,
                formationElement.player.name,
                formationElement.position
            )
        }
    }
}
