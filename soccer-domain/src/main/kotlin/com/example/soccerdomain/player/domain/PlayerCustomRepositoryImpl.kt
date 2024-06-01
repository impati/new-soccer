package com.example.soccerdomain.player.domain

import com.example.soccerdomain.common.search.NumberOperator
import com.example.soccerdomain.common.search.OrderBy
import com.example.soccerdomain.player.NumberSearchInput
import com.example.soccerdomain.player.PlayerSearchInput
import com.example.soccerdomain.player.domain.QPlayer.player
import com.example.soccerdomain.team.domain.QTeam.team
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.util.StringUtils
import java.util.*

class PlayerCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : PlayerCustomRepository {

    override fun search(playerSearchInput: PlayerSearchInput): List<Player> {
        return queryFactory.select(player)
            .from(player)
            .leftJoin(player.team, team)
            .where(
                eqName(playerSearchInput.name),
                eqTeamName(playerSearchInput.teamName),
                operatorRating(playerSearchInput.rating),
                containFoot(playerSearchInput.mainFoot),
                eqPosition(playerSearchInput.position),
                anyMatchTrait(playerSearchInput.trait)
            )
            .orderBy(orderByRating(playerSearchInput.rating))
            .fetch()
    }

    private fun orderByRating(numberSearchInput: NumberSearchInput?): OrderSpecifier<Int>? {
        if (Objects.isNull(numberSearchInput)) {
            return player.rating.value.desc()
        }
        if (Objects.isNull(numberSearchInput!!.orderBy)) {
            return player.rating.value.desc()
        }

        if (numberSearchInput.orderBy == OrderBy.ASC) {
            return player.rating.value.asc()
        }
        return player.rating.value.desc()
    }

    private fun eqName(name: String?): BooleanExpression? {
        if (!StringUtils.hasText(name)) {
            return null
        }

        return player.name.like(name)
    }

    private fun eqTeamName(teamName: String?): BooleanExpression? {
        if (!StringUtils.hasText(teamName)) {
            return null
        }

        return team.name.contains(teamName)
    }

    private fun operatorRating(numberSearchInput: NumberSearchInput?): BooleanExpression? {
        if (Objects.isNull(numberSearchInput)) {
            return null
        }

        return when (numberSearchInput!!.operator) {
            NumberOperator.LESS_THAN -> player.rating.value.lt(numberSearchInput.value)
            NumberOperator.MORE_THAN -> player.rating.value.gt(numberSearchInput.value)
            NumberOperator.EQUAL -> player.rating.value.eq(numberSearchInput.value)
            NumberOperator.LESS_THAN_EQUAL -> player.rating.value.loe(numberSearchInput.value)
            NumberOperator.MORE_THAN_EQUAL -> player.rating.value.goe(numberSearchInput.value)
        }
    }

    private fun containFoot(mainFoots: List<MainFoot>?): BooleanExpression? {
        if (Objects.isNull(mainFoots) || mainFoots!!.isEmpty()) {
            return null
        }

        return player.mainFoot.`in`(mainFoots)
    }

    private fun eqPosition(position: Position?): BooleanExpression? {
        if (Objects.isNull(position)) {
            return null
        }

        return player.position.contains(position)
    }

    private fun anyMatchTrait(traits: List<Trait>?): BooleanExpression? {
        if (Objects.isNull(traits) || traits!!.isEmpty()) {
            return null
        }


        var combinedExpression: BooleanExpression? = null
        for (trait in traits) {
            val currentExpression = player.trait.contains(trait)

            combinedExpression = if (combinedExpression == null) {
                currentExpression
            } else {
                combinedExpression.or(currentExpression)
            }
        }
        return combinedExpression

    }
}
