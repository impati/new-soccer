package com.example.soccer.player.domain

import jakarta.persistence.*
import lombok.Getter


@Getter
@Entity
@Table(name = "players")
class Player(

    var name: String,

    @ElementCollection(targetClass = Position::class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "player_positions", joinColumns = [JoinColumn(name = "player_id")])
    @Column(name = "position")
    var position: Set<Position>,

    @Embedded
    var stat: Stat,

    @ElementCollection(targetClass = Trait::class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "player_trait", joinColumns = [JoinColumn(name = "player_id")])
    @Column(name = "trait")
    var trait: Set<Trait>,

    @Enumerated(EnumType.STRING)
    var mainFoot: MainFoot,

    var rating: Rating = Rating(1000),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    val id: Long? = null
) {
    fun edit(name: String, position: Set<Position>, stat: Stat, trait: Set<Trait>, mainFoot: MainFoot) {
        this.name = name
        this.position = position
        this.stat = stat
        this.trait = trait
        this.mainFoot = mainFoot
    }
}
