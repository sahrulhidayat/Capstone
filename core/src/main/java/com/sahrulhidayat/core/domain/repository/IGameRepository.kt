package com.sahrulhidayat.core.domain.repository

import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getAllGames(sort: String): Flow<Resource<List<GameModel>>>

    fun getAllFavoriteGames(): Flow<List<GameModel>>

    fun getSearchGame(name: String): Flow<List<GameModel>>

    fun setFavoriteGame(game: GameModel, state: Boolean)
}