package com.example.soccer.player.service

import com.example.soccer.player.api.request.PlayerRequest
import com.example.soccer.player.api.response.PlayerResponse
import com.example.soccer.player.domain.PlayerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PlayerService(
    val playerRepository: PlayerRepository
) {

    fun create(request: PlayerRequest): PlayerResponse {
        return PlayerResponse.from(playerRepository.save(request.toEntity()))
    }

    fun edit(playerId: Long, request: PlayerRequest): PlayerResponse {
        val player = playerRepository.findById(playerId).orElseThrow()

        player.edit(
            request.name,
            request.position,
            request.stat,
            request.trait,
            request.mainFoot
        )

        return PlayerResponse.from(player)
    }

    fun get(playerId: Long): PlayerResponse {
        return PlayerResponse.from(playerRepository.findById(playerId).orElseThrow())
    }

    fun delete(playerId: Long) {
        playerRepository.deleteById(playerId)
    }
}
