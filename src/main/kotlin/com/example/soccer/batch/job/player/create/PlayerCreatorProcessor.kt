package com.example.soccer.batch.job.player.create

import com.example.soccer.player.domain.*
import org.springframework.batch.item.ItemProcessor

class PlayerCreatorProcessor : ItemProcessor<InputPlayer, Player> {
    override fun process(item: InputPlayer): Player {
        val positions = item.position.split(":").toList().map { Position.valueOf(it) }.toSet()
        val trait = item.trait.split(":").toList().map { Trait.valueOf(it) }.toSet()

        return Player(
            name = item.name,
            position = positions,
            stat = Stat(),
            trait = trait,
            mainFoot = MainFoot.valueOf(item.mainFoot)
        )
    }
}
