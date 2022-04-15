package com.sahrulhidayat.core.data.source

import com.sahrulhidayat.core.data.source.local.LocalDataSource
import com.sahrulhidayat.core.data.source.remote.RemoteDataSource
import com.sahrulhidayat.core.utils.SortUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class GameRepositoryTest {

    private lateinit var testDispatchers: CoroutineDispatcher
    private val sort = SortUtils.NEWEST
    private val localSource = mock<LocalDataSource>()
    private val remoteSource = mock<RemoteDataSource>()
    private val repository = mock<GameRepository>()

    @Before
    fun setUp() {
        testDispatchers = UnconfinedTestDispatcher()
    }

    @Test
    fun getGameList() {
    }

    @Test
    fun getGameDetails() {
    }

    @Test
    fun getAllFavoriteGames() {
    }
}