package com.sahrulhidayat.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameDetailsResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("background_image")
    val backgroundImage: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("released")
    val released: String,

    @field:SerializedName("genres")
    val genres: List<GameGenres>,

    @field:SerializedName("tags")
    val tags: List<GameTags>,

    @field:SerializedName("description_raw")
    val descriptionRaw: String,
)

data class GameTags(
    @field:SerializedName("name")
    val name: String,
)