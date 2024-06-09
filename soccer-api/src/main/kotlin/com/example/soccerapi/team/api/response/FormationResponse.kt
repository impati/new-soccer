package com.example.soccerapi.team.api.response

import com.example.soccerdomain.team.domain.formation.Formation

data class FormationResponse(

    val formationElements: List<FormationElementResponse>,
    val formationId: Long,
    val formationName: String
) {


    companion object {

        fun from(formation: Formation): FormationResponse {
            return FormationResponse(
                formation.formationElements.map { FormationElementResponse.from(it) },
                formation.id!!,
                formation.formationName
            )
        }
    }
}
