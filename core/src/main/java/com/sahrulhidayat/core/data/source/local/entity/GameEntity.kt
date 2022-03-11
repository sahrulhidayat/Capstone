package com.sahrulhidayat.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_entities")
data class GameEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "background")
    val background: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "released")
    val released: String,

    @ColumnInfo(name = "genres")
    val genres: String,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
)