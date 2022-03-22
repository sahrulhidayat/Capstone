package com.sahrulhidayat.core.data.source.remote.network

import com.sahrulhidayat.core.BuildConfig
import com.sahrulhidayat.core.data.source.remote.response.GameDetailsResponse
import com.sahrulhidayat.core.data.source.remote.response.GameListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGameList(
        @Query("ordering") ordering: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY,
    ): GameListResponse

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") id: Int,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): GameDetailsResponse

}