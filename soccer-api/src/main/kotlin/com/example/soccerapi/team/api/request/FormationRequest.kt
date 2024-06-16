package com.example.soccerapi.team.api.request

import org.hibernate.validator.constraints.Range

data class FormationRequest(

    val name: String,
    @field:Range(min = 11, max = 11)
    val elements: List<FormationElementRequest>
)
