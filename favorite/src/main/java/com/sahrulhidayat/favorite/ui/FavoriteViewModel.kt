package com.sahrulhidayat.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.domain.usecase.GameUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class FavoriteViewModel(
    private val mainDispatcher: CoroutineDispatcher,
    private val gameUseCase: GameUseCase
) : ViewModel() {

    val favoriteGames: LiveData<List<GameModel>> = getAllFavoriteGame().asLiveData()

    fun getAllFavoriteGame(): Flow<List<GameModel>> {
        return gameUseCase.getAllFavoriteGames().flowOn(mainDispatcher)
    }
}