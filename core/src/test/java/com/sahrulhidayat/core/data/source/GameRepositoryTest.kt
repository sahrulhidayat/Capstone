package com.sahrulhidayat.core.data.source

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sahrulhidayat.core.data.source.local.LocalDataSource
import com.sahrulhidayat.core.data.source.remote.RemoteDataSource
import com.sahrulhidayat.core.utils.SortUtils
import com.sahrulhidayat.utils.DummyGames.generateDummyGameList
import com.sahrulhidayat.utils.DummyGames.generateRemoteDummyGameDetails
import com.sahrulhidayat.utils.DummyGames.generateRemoteDummyGameList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class GameRepositoryTest {

    private lateinit var testDispatchers: CoroutineDispatcher
    private lateinit var repository: GameRepository

    private val sort = SortUtils.NEWEST

    private val gameListResponse = generateRemoteDummyGameList()
    private val gameDetailsResponse = generateRemoteDummyGameDetails()

    private val gameId = gameListResponse[0].id

    private val dummyGameList = generateDummyGameList()
    private val gameList = flow {
        emit(dummyGameList)
    }

    private val dummyGame = generateDummyGameList()[0]
    private val gameDetails = flow {
        emit(dummyGame)
    }

    private val remoteSource = mock<RemoteDataSource>()
    private val localSource = mock<LocalDataSource> {
        on { getGameList(sort) } doReturn (gameList)
        on { getGameDetails(gameId) } doReturn (gameDetails)
        on { getAllFavoriteGames() } doReturn (gameList)
    }

    val gameEntities = flow {
        emit(Resource.Success(dummyGameList))
    }

    @Before
    fun setUp() {
        testDispatchers = UnconfinedTestDispatcher()
        repository = GameRepository(remoteSource, localSource, testDispatchers)
    }

    @Test
    fun getGameList() = runBlocking {
        repository.stub {
            on { getGameList(sort) } doReturn(gameEntities)
        }
        verify(localSource).getGameList(sort)

        val job = launch {
            repository.getGameList(sort).test {
                val item = awaitItem()
                assertThat(item).isNotNull()
                assertThat(item.data?.size).isEqualTo(gameListResponse.size)
                cancelAndConsumeRemainingEvents()
            }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun getGameDetails() = runBlocking {
        repository.getGameDetails(gameId).test {
            val item = awaitItem()
            assertThat(item).isNotNull()
            assertThat(item.data?.id).isEqualTo(gameDetailsResponse.id)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun getAllFavoriteGames() = runBlocking {
        repository.getAllFavoriteGames().test {
            val item = awaitItem()
            assertThat(item).isNotNull()
            assertThat(item.size).isEqualTo(gameListResponse.size)
            cancelAndConsumeRemainingEvents()
        }
    }
}