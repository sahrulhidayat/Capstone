package com.sahrulhidayat.settings.ui

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sahrulhidayat.core.domain.usecase.PreferenceUseCase
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
class SettingsViewModelTest {

    private lateinit var testDispatchers: CoroutineDispatcher
    private lateinit var viewModel: SettingsViewModel

    private val dummyFlow = flow {
        emit(false)
    }

    private val preferenceUseCase = mock<PreferenceUseCase> {
        on { getThemeSettings() } doReturn (dummyFlow)
    }

    @Before
    fun setUp() {
        testDispatchers = UnconfinedTestDispatcher()
        viewModel = SettingsViewModel(testDispatchers, preferenceUseCase)
    }

    @Test
    fun `getThemeSettingsFlow(), should return correct flow`() = runBlocking {
        viewModel.getThemeSettingsFlow().test {
            val item = awaitItem()
            assertThat(item).isEqualTo(false)
            cancelAndConsumeRemainingEvents()
        }
    }
}