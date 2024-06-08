package com.example.soccerdomain.player.domain

import jakarta.persistence.Embeddable


@Embeddable
class Stat(

    val basic: Basic,
    val physical: Physical,
    val pass: Pass,
    val forward: Forward,
    val defense: Defense,
    val goalKeeping: GoalKeeping
)
