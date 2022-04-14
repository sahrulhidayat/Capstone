package com.sahrulhidayat.details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.domain.usecase.GameUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class DetailsViewModel(
    private val mainDispatcher: CoroutineDispatcher,
    private val gameUseCase: GameUseCase
) : ViewModel() {

    fun getGameDetails(id: Int): LiveData<Resource<GameModel>> = getGameDetailsFlow(id).asLiveData()

    fun getGameDetailsFlow(id: Int): Flow<Resource<GameModel>> {
        return gameUseCase.getGameDetails(id).flowOn(mainDispatcher)
    }

    fun setFavoriteGame(game: GameModel, newState: Boolean) {
        return gameUseCase.setFavoriteGame(game, newState)
    }
}