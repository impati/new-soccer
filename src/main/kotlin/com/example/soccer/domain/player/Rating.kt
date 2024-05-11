package com.example.soccer.domain.player

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Rating (

    @Column(name = "rating")
    val value : Int
){

    fun getRatingColor() : String {
        TODO()
    }
}
