package com.example.soccer.player.api

import com.example.soccer.player.api.request.PlayerRequest
import com.example.soccer.player.api.response.PlayerResponse
import com.example.soccer.player.service.PlayerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/player")
class PlayerController(
    val playerService: PlayerService
) {

    @PostMapping
    fun create(@RequestBody request: PlayerRequest): PlayerResponse {
        return playerService.create(request)
    }

    @PutMapping("/{playerId}")
    fun edit(@PathVariable playerId: Long, @RequestBody request: PlayerRequest): PlayerResponse {
        return playerService.edit(playerId, request)
    }

    @DeleteMapping("/{playerId}")
    fun delete(@PathVariable playerId: Long) {
        playerService.delete(playerId)
    }

    @GetMapping("/{playerId}")
    fun get(@PathVariable playerId: Long): PlayerResponse {
        return playerService.get(playerId)
    }

}
