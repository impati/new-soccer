package com.example.soccerdomain.team.domain.formation

import com.example.soccerdomain.player.domain.Player
import com.example.soccerdomain.player.domain.Position
import jakarta.persistence.*

@Entity
@Table(name = "formation_elements")
class FormationElement(

    var position: Position,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    var player: Player,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formation_id")
    val formation: Formation,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formation_element_id")
    var id: Long?
)
