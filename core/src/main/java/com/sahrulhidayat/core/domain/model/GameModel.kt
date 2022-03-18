package com.sahrulhidayat.core.domain.model

data class GameModel(
    val id: Int,
    val name: String,
    val background: String,
    val rating: Double? = null,
    val released: String? = null,
    val genres: String? = null,
    val tags: String? = null,
    val description: String? = null,
    var isFavorite: Boolean = false,
)