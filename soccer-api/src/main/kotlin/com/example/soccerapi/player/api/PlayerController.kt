package com.example.soccerapi.player.api

import com.example.soccerapi.player.api.request.PlayerRequest
import com.example.soccerapi.player.api.response.PlayerResponse
import com.example.soccerapi.player.application.PlayerFacade
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/player")
class PlayerController(
    val playerFacade: PlayerFacade
) {

    @PostMapping
    fun create(@RequestBody request: PlayerRequest): PlayerResponse {
        return playerFacade.create(request)
    }

    @PutMapping("/{playerId}")
    fun edit(@PathVariable playerId: Long, @RequestBody request: PlayerRequest): PlayerResponse {
        return playerFacade.edit(playerId, request)
    }

    @DeleteMapping("/{playerId}")
    fun delete(@PathVariable playerId: Long) {
        playerFacade.delete(playerId)
    }

    @GetMapping("/{playerId}")
    fun get(@PathVariable playerId: Long): PlayerResponse {
        return playerFacade.get(playerId)
    }

}
