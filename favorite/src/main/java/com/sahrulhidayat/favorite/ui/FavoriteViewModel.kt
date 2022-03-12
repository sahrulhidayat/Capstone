package com.sahrulhidayat.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.domain.usecase.GameUseCase

class FavoriteViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun getAllFavoriteGame(): LiveData<List<GameModel>> {
        return gameUseCase.getAllFavoriteGames().asLiveData()
    }
}