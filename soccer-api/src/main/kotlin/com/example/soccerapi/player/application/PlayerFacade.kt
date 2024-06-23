package com.example.soccerapi.player.application

import com.example.soccerapi.player.api.request.PlayerRequest
import com.example.soccerapi.player.api.request.PlayerSearchRequest
import com.example.soccerapi.player.api.response.PlayerResponse
import com.example.soccerdomain.player.domain.PlayerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PlayerFacade(
    val playerRepository: PlayerRepository
) {

    fun create(request: PlayerRequest): PlayerResponse {
        return PlayerResponse.from(playerRepository.save(request.toEntity()))
    }

    fun edit(playerId: Long, request: PlayerRequest): PlayerResponse {
        val player = playerRepository.findById(playerId).orElseThrow()

        player.edit(
            request.name,
            request.positions,
            request.stat,
            request.traits,
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

    fun search(request: PlayerSearchRequest): List<PlayerResponse> {
        return playerRepository.search(request.toInput())
            .map { PlayerResponse.from(it) }
            .toList()
    }
}
