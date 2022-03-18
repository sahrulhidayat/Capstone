package com.sahrulhidayat.core.utils

import com.sahrulhidayat.core.data.source.local.entity.GameEntity
import com.sahrulhidayat.core.data.source.remote.response.GameDetailsResponse
import com.sahrulhidayat.core.data.source.remote.response.GameResults
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.utils.DataFormatter.genresString
import com.sahrulhidayat.core.utils.DataFormatter.tagsString

object DataMapper {
    fun mapGameListResponseToEntities(input: List<GameResults>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                id = it.id,
                name = it.name,
                background = it.backgroundImage,
                rating = it.rating,
                genres = genresString(it.genres),
                isFavorite = false,
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapGameDetailsResponseToEntities(input: GameDetailsResponse): GameEntity {
        return GameEntity(
            id = input.id,
            name = input.name,
            background = input.backgroundImage,
            rating = input.rating,
            released = input.released,
            genres = genresString(input.genres),
            tags = tagsString(input.tags),
            description = input.descriptionRaw,
            isFavorite = false,
        )
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
                tags = it.tags,
                description = it.description,
                isFavorite = it.isFavorite,
            )
        }
    }

    fun mapDetailsEntityToDomain(input: GameEntity): GameModel {
        return GameModel(
            id = input.id,
            name = input.name,
            background = input.background,
            rating = input.rating,
            released = input.released,
            genres = input.genres,
            tags = input.tags,
            description = input.description,
            isFavorite = input.isFavorite,
        )
    }

    fun mapDomainToEntity(input: GameModel): GameEntity {
        return GameEntity(
            input.id,
            input.name,
            input.background,
            input.rating,
            input.released,
            input.genres,
            input.tags,
            input.description,
            input.isFavorite,
        )
    }
}