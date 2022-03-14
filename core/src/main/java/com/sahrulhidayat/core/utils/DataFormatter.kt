package com.sahrulhidayat.core.utils

import com.sahrulhidayat.core.data.source.remote.response.GameGenres

object DataFormatter {
    fun genresString(genres: List<GameGenres>): String {
        val result = ArrayList<String>()
        for (item in genres) {
            result.add(item.name)
        }
        return result.joinToString()
    }
}