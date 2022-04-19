package com.sahrulhidayat.favorite.ui

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.domain.usecase.GameUseCase
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
class FavoriteViewModelTest {

    private lateinit var testDispatchers: CoroutineDispatcher
    private lateinit var viewModel: FavoriteViewModel

    private val gameList = mock<List<GameModel>> {
        on { size } doReturn (2)
    }

    private val dummyFlow = flow {
        emit(gameList)
    }

    private val gameUseCase = mock<GameUseCase> {
        on { getAllFavoriteGames() } doReturn (dummyFlow)
    }

    @Before
    fun setUp() {
        testDispatchers = UnconfinedTestDispatcher()
        viewModel = FavoriteViewModel(testDispatchers, gameUseCase)
    }

    @Test
    fun `getAllFavoriteGame(), should return correct flow`() = runBlocking {
        viewModel.getAllFavoriteGame().test {
            val item = awaitItem()
            assertThat(item).isNotNull()
            assertThat(item.size).isEqualTo(2)
            cancelAndConsumeRemainingEvents()
        }
    }
}