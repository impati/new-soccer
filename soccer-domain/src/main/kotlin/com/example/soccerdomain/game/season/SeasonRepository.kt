package com.example.soccerdomain.game.season

import com.example.soccerdomain.game.Season
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface SeasonRepository : JpaRepository<Season, Long> {

    @Query("select s from Season s order by s.value desc limit 1")
    fun findLastSeason(): Optional<Season>
}
