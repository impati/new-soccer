package com.example.soccerdomain.player.domain

import com.example.soccerdomain.team.domain.Team
import jakarta.persistence.*
import lombok.Getter


@Getter
@Entity
@Table(name = "players")
class Player(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = true)
    var team: Team? = null,

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

    fun join(team: Team) {
        this.team = team
    }

    fun leave(team: Team) {
        require(this.team == team) { "떠날 수 없는 팀입니다." }
        this.team = null
    }

    fun edit(name: String, position: Set<Position>, stat: Stat, trait: Set<Trait>, mainFoot: MainFoot) {
        this.name = name
        this.position = position
        this.stat = stat
        this.trait = trait
        this.mainFoot = mainFoot
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
