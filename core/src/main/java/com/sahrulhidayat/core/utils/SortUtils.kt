package com.sahrulhidayat.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "newest"
    const val RANDOM = "oldest"
    const val RATING = "rating"

    fun getSortedQueryGames(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM game_entities ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY released DESC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
            RATING -> {
                simpleQuery.append("ORDER BY rating DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}