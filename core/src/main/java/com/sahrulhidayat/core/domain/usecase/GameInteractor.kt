package com.sahrulhidayat.core.domain.usecase

import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.domain.interfaces.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository) : GameUseCase {
    override fun getGameList(sort: String): Flow<Resource<List<GameModel>>> {
        return gameRepository.getGameList(sort)
    }

    override fun getGameDetails(id: Int): Flow<Resource<GameModel>> {
        return gameRepository.getGameDetails(id)
    }

    override fun getAllFavoriteGames(): Flow<List<GameModel>> {
        return gameRepository.getAllFavoriteGames()
    }

    override fun setFavoriteGame(game: GameModel, state: Boolean) {
        return gameRepository.setFavoriteGame(game, state)
    }
}