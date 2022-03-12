package com.sahrulhidayat.capstone.detail

import androidx.lifecycle.ViewModel
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.domain.usecase.GameUseCase

class DetailsViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun setFavoriteGame(game: GameModel, newState: Boolean) {
        return gameUseCase.setFavoriteGame(game, newState)
    }
}