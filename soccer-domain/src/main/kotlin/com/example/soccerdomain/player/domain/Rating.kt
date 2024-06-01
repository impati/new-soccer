package com.example.soccerdomain.player.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Rating(

    @Column(name = "rating")
    val value: Int


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rating

        return value == other.value
    }

    override fun hashCode(): Int {
        return value
    }
}
