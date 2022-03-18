package com.sahrulhidayat.core.utils

import com.sahrulhidayat.core.data.source.remote.response.GameGenres
import com.sahrulhidayat.core.data.source.remote.response.GameTags

object DataFormatter {
    fun genresString(genres: List<GameGenres>): String {
        val result = ArrayList<String>()
        for (item in genres) {
            result.add(item.name)
        }
        return result.joinToString()
    }

    fun tagsString(genres: List<GameTags>): String {
        val result = ArrayList<String>()
        for (item in genres) {
            result.add(item.name)
        }
        return result.joinToString()
    }
}