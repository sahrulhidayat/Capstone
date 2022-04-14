package com.sahrulhidayat.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.domain.usecase.GameUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class HomeViewModel(
    private val mainDispatcher: CoroutineDispatcher,
    private val gameUseCase: GameUseCase
) : ViewModel() {

    private var mSort = ""
    fun setSort(sort: String) {
        mSort = sort
    }

    val gameList: LiveData<Resource<List<GameModel>>> = getGameList(mSort).asLiveData()

    fun getGameList(sort: String): Flow<Resource<List<GameModel>>> {
       return gameUseCase.getGameList(sort).flowOn(mainDispatcher)
    }

}