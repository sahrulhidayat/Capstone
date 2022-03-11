package com.sahrulhidayat.core.domain.usecase

import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getAllGames(sort: String): Flow<Resource<List<GameModel>>> {
        return gameRepository.getAllGames(sort)
    }

    override fun getAllFavoriteGames(): Flow<List<GameModel>> {
        return gameRepository.getAllFavoriteGames()
    }

    override fun getSearchGame(name: String): Flow<List<GameModel>> {
        return gameRepository.getSearchGame(name)
    }

    override fun setFavoriteGame(game: GameModel, state: Boolean) {
        return gameRepository.setFavoriteGame(game, state)
    }
}