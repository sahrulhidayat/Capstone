package com.sahrulhidayat.core.data.source.local

import com.sahrulhidayat.core.data.source.local.entity.GameEntity
import com.sahrulhidayat.core.data.source.local.room.GameDao
import com.sahrulhidayat.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val mGameDao: GameDao) {

    fun getAllGames(sort: String): Flow<List<GameEntity>> {
        val query = SortUtils.getSortedQueryGames(sort)
        return mGameDao.getAllGames(query)
    }

    fun getAllFavoriteGames(): Flow<List<GameEntity>> {
        return mGameDao.getAllFavoriteGames()
    }

    fun setFavoriteGame(game: GameEntity, state: Boolean) {
        game.isFavorite = state
        mGameDao.updateDataGame(game)
    }

    fun getSearchGames(name: String): Flow<List<GameEntity>> {
        return mGameDao.getSearchResult(name)
    }

    suspend fun insertGame(game: List<GameEntity>) = mGameDao.insertDataGame(game)
}