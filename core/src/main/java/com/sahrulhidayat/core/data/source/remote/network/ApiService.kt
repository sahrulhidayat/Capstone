package com.sahrulhidayat.core.data.source.remote.network

import com.sahrulhidayat.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getListGames(): ListGameResponse
}