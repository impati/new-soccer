package com.example.soccerdomain.team.domain.formation

import com.example.soccerdomain.team.domain.Team
import jakarta.persistence.*

/**
 * 팀은 여러 개의 포메이션을 가질 수 있다.
 */
@Entity
@Table(name = "formations")
class Formation(

    @Column(name = "formation_name")
    var formationName: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    val team: Team,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formation_id")
    var id: Long?
)
