package com.sahrulhidayat.core.data.source.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.sahrulhidayat.core.BuildConfig
import com.sahrulhidayat.core.data.source.remote.network.ApiResponse
import com.sahrulhidayat.core.data.source.remote.network.ApiService
import com.sahrulhidayat.core.data.source.remote.response.GameDetailsResponse
import com.sahrulhidayat.core.data.source.remote.response.GameResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    private val apiKey = BuildConfig.API_KEY

    suspend fun getGameList(): Flow<ApiResponse<List<GameResults>>> {
        return flow {
            try {
                val response = apiService.getGameList(apiKey)
                val gameList = response.results
                if (gameList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getGameList: $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGameDetails(id: Int): Flow<ApiResponse<GameDetailsResponse>> {
        return flow {
            try {
                val response = apiService.getGameDetails(id, apiKey)
                if (response.descriptionRaw.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getGameDetails: $e")
            }
        }
    }
}