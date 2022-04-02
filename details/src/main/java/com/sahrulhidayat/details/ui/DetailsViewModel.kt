package com.sahrulhidayat.details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.domain.usecase.GameUseCase

class DetailsViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun getGameDetails(id: Int): LiveData<Resource<GameModel>> {
        return gameUseCase.getGameDetails(id).asLiveData()
    }

    fun setFavoriteGame(game: GameModel, newState: Boolean) {
        return gameUseCase.setFavoriteGame(game, newState)
    }
}