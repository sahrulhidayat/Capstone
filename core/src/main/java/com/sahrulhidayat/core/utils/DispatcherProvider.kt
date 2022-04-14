package com.sahrulhidayat.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatcherProvider {
    fun main(): CoroutineDispatcher = Dispatchers.Main
}