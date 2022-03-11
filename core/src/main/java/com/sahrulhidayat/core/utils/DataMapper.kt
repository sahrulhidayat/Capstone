package com.sahrulhidayat.core.utils

import com.sahrulhidayat.core.data.source.local.entity.GameEntity
import com.sahrulhidayat.core.data.source.remote.response.GameResponse
import com.sahrulhidayat.core.domain.model.GameModel

object DataMapper {
    fun mapGameResponseToEntities(input: List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                id = it.id,
                name = it.name,
                background = it.backgroundImage,
                rating = it.rating,
                released = it.released,
                genres = it.genres,
                description = it.descriptionRaw,
                isFavorite = false,
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<GameModel> {
        return input.map {
            GameModel(
            id = it.id,
            name = it.name,
            background = it.background,
            rating = it.rating,
            released = it.released,
            genres = it.genres,
            description = it.description,
            isFavorite = it.isFavorite,
            )
        }
    }

    fun mapDomainToEntities(input: GameModel): GameEntity {
        return GameEntity(
            input.id,
            input.name,
            input.background,
            input.rating,
            input.released,
            input.genres,
            input.description,
            input.isFavorite,
        )
    }
}