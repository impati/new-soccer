package com.example.soccer.team.domain

import com.example.soccer.player.domain.Player
import com.example.soccer.player.domain.Rating
import jakarta.persistence.*

@Entity
@Table(name = "teams")
class Team(

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    var player: List<Player> = mutableListOf(),

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
}
