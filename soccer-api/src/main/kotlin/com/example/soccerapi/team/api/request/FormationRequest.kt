package com.example.soccerapi.team.api.request

data class FormationRequest(

    val name: String,
    val elements: List<FormationElementRequest>
)
