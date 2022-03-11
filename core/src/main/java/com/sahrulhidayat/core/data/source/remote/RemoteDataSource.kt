package com.sahrulhidayat.core.data.source.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.sahrulhidayat.core.BuildConfig
import com.sahrulhidayat.core.data.source.remote.network.ApiResponse
import com.sahrulhidayat.core.data.source.remote.network.ApiService
import com.sahrulhidayat.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    private val apiKey = BuildConfig.API_KEY

    suspend fun getGames(): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = apiService.getGames(apiKey)
                val gameList = response.results
                if (gameList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getGames: $e")
            }
        }.flowOn(Dispatchers.IO)
    }
}