package com.example.soccerdomain.player.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Rating(

    @Column(name = "rating")
    val value: Int
)
