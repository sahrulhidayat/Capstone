package com.sahrulhidayat.utils

import com.sahrulhidayat.core.data.source.local.entity.GameEntity
import com.sahrulhidayat.core.data.source.remote.response.GameDetailsResponse
import com.sahrulhidayat.core.data.source.remote.response.GameGenres
import com.sahrulhidayat.core.data.source.remote.response.GameResults
import com.sahrulhidayat.core.data.source.remote.response.GameTags

object DummyGames {
    fun generateDummyGameList(): List<GameEntity> {
        val games = ArrayList<GameEntity>()
        games.add(
            GameEntity(
                0,
                "Portal",
                "https://media.rawg.io/media/games/7fa/7fa0b586293c5861ee32490e953a4996.jpg",
                4.51,
                "2007-10-09",
                "Adventure, Puzzle",
                "Singleplayer, Story Rich",
                "Lorem Ipsum"
            )
        )
        games.add(
            GameEntity(
                1,
                "Limbo",
                "https://media.rawg.io/media/games/942/9424d6bb763dc38d9378b488603c87fa.jpg",
                4.17,
                "2010-07-21",
                "Adventure, Horror",
                "Singleplayer, Horror",
                "Lorem Ipsum"
            )
        )
        return games
    }

    fun generateRemoteDummyGameList(): List<GameResults> {
        val games = ArrayList<GameResults>()
        games.add(
            GameResults(
                0,
                "Portal",
                "https://media.rawg.io/media/games/7fa/7fa0b586293c5861ee32490e953a4996.jpg",
                4.51,
                "2007-10-09",
                listOf(GameGenres("Adventure"), GameGenres("Puzzle"))
            )
        )
        games.add(
            GameResults(
                1,
                "Limbo",
                "https://media.rawg.io/media/games/942/9424d6bb763dc38d9378b488603c87fa.jpg",
                4.17,
                "2010-07-21",
                listOf(GameGenres("Adventure"), GameGenres("Horror")),
            )
        )
        return games
    }

    fun generateRemoteDummyGameDetails(): GameDetailsResponse {
        return GameDetailsResponse(
            0,
            "Portal",
            "https://media.rawg.io/media/games/7fa/7fa0b586293c5861ee32490e953a4996.jpg",
            4.51,
            "2007-10-09",
            listOf(GameGenres("Adventure"), GameGenres("Puzzle")),
            listOf(GameTags("Singleplayer"), GameTags("Story Rich")),
            "Lorem Ipsum"
        )

    }
}