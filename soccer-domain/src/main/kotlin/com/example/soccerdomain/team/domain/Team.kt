package com.example.soccerdomain.team.domain

import com.example.soccerdomain.player.domain.Player
import com.example.soccerdomain.player.domain.Rating
import jakarta.persistence.*

@Entity
@Table(name = "teams")
class Team(

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    var player: MutableList<Player> = mutableListOf(),

    @Enumerated(EnumType.STRING)
    val league: League,

    @Column(name = "name")
    val name: String,

    val rating: Rating = Rating(1000),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    val id: Long? = null
) {

    fun hiring(players: List<Player>) {
        for (player in players) {
            player.join(this)
            this.player.add(player)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Team

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
