package com.sahrulhidayat.capstone.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.domain.usecase.GameUseCase

class HomeViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun getGameList(sort: String): LiveData<Resource<List<GameModel>>> {
        return gameUseCase.getGameList(sort).asLiveData()
    }
}