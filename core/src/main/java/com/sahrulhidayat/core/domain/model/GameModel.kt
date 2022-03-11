package com.sahrulhidayat.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameModel(
    val id: Int,
    val name: String,
    val background: String,
    val rating: Double,
    val released: String,
    val genres: String,
    val description: String? = null,
    var isFavorite: Boolean = false,
) : Parcelable