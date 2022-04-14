package com.sahrulhidayat.home.ui

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.domain.usecase.GameUseCase
import com.sahrulhidayat.core.utils.SortUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var testDispatchers: CoroutineDispatcher
    private lateinit var viewModel: HomeViewModel
    private val sort = SortUtils.NEWEST

    private val gameList = mock<List<GameModel>> {
        on { size } doReturn(2)
    }

    private val dummyFlow = flow {
        emit(Resource.Success(gameList))
    }

    private val gameUseCase = mock<GameUseCase> {
        on { getGameList(sort) } doReturn(dummyFlow)
    }

    @Before
    fun setUp() {
        testDispatchers = UnconfinedTestDispatcher()
        viewModel = HomeViewModel(testDispatchers, gameUseCase)
    }

    @Test
    fun getGameList() = runBlocking {
        viewModel.getGameListFlow(sort).test {
            val item = awaitItem()
            assertThat(item).isNotNull()
            assertThat(item.data?.size).isEqualTo(2)
            cancelAndConsumeRemainingEvents()
        }
    }
}