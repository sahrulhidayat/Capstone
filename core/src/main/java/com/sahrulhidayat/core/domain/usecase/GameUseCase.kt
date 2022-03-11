package com.sahrulhidayat.core.domain.usecase

import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGames(sort: String): Flow<Resource<List<GameModel>>>

    fun getAllFavoriteGames(): Flow<List<GameModel>>

    fun getSearchGame(name: String): Flow<List<GameModel>>

    fun setFavoriteGame(game: GameModel, state: Boolean)
}