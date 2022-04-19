package com.sahrulhidayat.core.data.source

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sahrulhidayat.core.data.source.local.LocalDataSource
import com.sahrulhidayat.core.utils.DataFormatter.genresString
import com.sahrulhidayat.core.utils.DataFormatter.tagsString
import com.sahrulhidayat.core.utils.SortUtils
import com.sahrulhidayat.utils.DummyGames.generateDummyGameList
import com.sahrulhidayat.utils.DummyGames.generateRemoteDummyGameDetails
import com.sahrulhidayat.utils.DummyGames.generateRemoteDummyGameList
import com.sahrulhidayat.utils.FakeGameRepository
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
class GameRepositoryTest {

    private lateinit var testDispatchers: CoroutineDispatcher
    private lateinit var repository: FakeGameRepository

    private val gameListResponse = generateRemoteDummyGameList()
    private val gameDetailsResponse = generateRemoteDummyGameDetails()

    private val dummyGameList = generateDummyGameList()
    private val gameList = flow {
        emit(dummyGameList)
    }

    private val dummyGame = generateDummyGameList()[0]
    private val gameDetails = flow {
        emit(dummyGame)
    }

    private val sort = SortUtils.NEWEST
    private val gameId = gameListResponse[0].id

    private val localSource = mock<LocalDataSource> {
        on { getGameList(sort) } doReturn (gameList)
        on { getGameDetails(gameId) } doReturn (gameDetails)
        on { getAllFavoriteGames() } doReturn (gameList)
    }

    @Before
    fun setUp() {
        testDispatchers = UnconfinedTestDispatcher()
        repository = FakeGameRepository(localSource, testDispatchers)
    }

    @Test
    fun getGameList() = runBlocking {
        repository.getGameList(sort).test {
            val item = awaitItem()
            assertThat(item.data).isNotNull()
            assertThat(item.data?.size).isEqualTo(gameListResponse.size)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun getGameDetails() = runBlocking {
        repository.getGameDetails(gameId).test {
            val item = awaitItem()
            assertThat(item.data).isNotNull()
            assertThat(item.data?.id).isEqualTo(gameDetailsResponse.id)
            assertThat(item.data?.background).isEqualTo(gameDetailsResponse.backgroundImage)
            assertThat(item.data?.name).isEqualTo(gameDetailsResponse.name)
            assertThat(item.data?.rating).isEqualTo(gameDetailsResponse.rating)
            assertThat(item.data?.released).isEqualTo(gameDetailsResponse.released)
            assertThat(item.data?.genres).isEqualTo(genresString(gameDetailsResponse.genres))
            assertThat(item.data?.tags).isEqualTo(tagsString(gameDetailsResponse.tags))
            assertThat(item.data?.description).isEqualTo(gameDetailsResponse.descriptionRaw)
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