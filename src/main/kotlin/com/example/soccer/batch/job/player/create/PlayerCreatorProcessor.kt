package com.example.soccer.batch.job.player.create

import com.example.soccer.player.domain.*
import org.springframework.batch.item.ItemProcessor

class PlayerCreatorProcessor : ItemProcessor<InputPlayer, Player> {
    override fun process(item: InputPlayer): Player {
        val positions = item.position.split(":").toList().map { Position.valueOf(it) }.toSet()
        val trait = item.trait.split(":").toList().map { Trait.valueOf(it) }.toSet()

        return Player(
            item.name,
            positions,
            Stat(),
            trait,
            MainFoot.valueOf(item.mainFoot)
        )
    }
}
