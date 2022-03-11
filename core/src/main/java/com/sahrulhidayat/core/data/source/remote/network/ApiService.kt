package com.sahrulhidayat.core.data.source.remote.network

import com.sahrulhidayat.core.BuildConfig
import com.sahrulhidayat.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGames(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("platforms") platform: String = "21"
    ): ListGameResponse
}