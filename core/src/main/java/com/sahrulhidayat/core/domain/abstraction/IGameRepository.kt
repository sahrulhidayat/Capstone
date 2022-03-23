package com.sahrulhidayat.core.domain.abstraction

import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getGameList(sort: String): Flow<Resource<List<GameModel>>>

    fun getGameDetails(id: Int): Flow<Resource<GameModel>>

    fun getAllFavoriteGames(): Flow<List<GameModel>>

    fun setFavoriteGame(game: GameModel, state: Boolean)
}