package com.sahrulhidayat.capstone.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.domain.usecase.GameUseCase

class SearchViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun getSearchedGames(name: String): LiveData<Resource<List<GameModel>>> {
        return gameUseCase.getSearchedGames(name).asLiveData()
    }
}