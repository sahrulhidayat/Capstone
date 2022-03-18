package com.sahrulhidayat.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameListResponse(
    @field:SerializedName("results")
    val results: List<GameResults>,
)

data class GameResults(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("background_image")
    val backgroundImage: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("genres")
    val genres: List<GameGenres>,
)

data class GameGenres(
    @field:SerializedName("name")
    val name: String,
)