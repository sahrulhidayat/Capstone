package com.sahrulhidayat.core.domain.usecase

import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getGameList(sort: String): Flow<Resource<List<GameModel>>>

    fun getGameDetails(id: Int): Flow<Resource<GameModel>>

    fun getAllFavoriteGames(): Flow<List<GameModel>>

    fun setFavoriteGame(game: GameModel, state: Boolean)
}