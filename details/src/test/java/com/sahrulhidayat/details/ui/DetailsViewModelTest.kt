package com.sahrulhidayat.details.ui

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sahrulhidayat.core.data.source.Resource
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
class DetailsViewModelTest {

    private lateinit var testDispatchers: CoroutineDispatcher
    private lateinit var viewModel: DetailsViewModel

    private val dummyId = 3
    private val game = mock<GameModel> {
        on { id } doReturn(dummyId)
    }

    private val dummyFlow = flow {
        emit(Resource.Success(game))
    }

    private val gameUseCase = mock<GameUseCase> {
        on { getGameDetails(dummyId) } doReturn(dummyFlow)
    }

    @Before
    fun setUp() {
        testDispatchers = UnconfinedTestDispatcher()
        viewModel = DetailsViewModel(testDispatchers, gameUseCase)
    }

    @Test
    fun `getGameDetailsFlow(), should return correct flow`() = runBlocking {
        viewModel.getGameDetailsFlow(dummyId).test {
            val item = awaitItem()
            assertThat(item).isNotNull()
            assertThat(item.data?.id).isEqualTo(dummyId)
            cancelAndConsumeRemainingEvents()
        }
    }
}