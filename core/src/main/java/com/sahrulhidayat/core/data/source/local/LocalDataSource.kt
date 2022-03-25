package com.sahrulhidayat.core.data.source.local

import com.sahrulhidayat.core.data.source.local.entity.GameEntity
import com.sahrulhidayat.core.data.source.local.room.GameDao
import com.sahrulhidayat.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val mGameDao: GameDao) {

    fun getGameList(sort: String): Flow<List<GameEntity>> {
        val query = SortUtils.getSortedQueryGames(sort)
        return mGameDao.getGameList(query)
    }

    fun getGameDetails(id: Int): Flow<GameEntity> {
        return mGameDao.getGameDetails(id)
    }

    fun getAllFavoriteGames(): Flow<List<GameEntity>> {
        return mGameDao.getAllFavoriteGames()
    }

    fun setFavoriteGame(game: GameEntity, state: Boolean) {
        game.isFavorite = state
        mGameDao.updateDataGame(game)
    }

    suspend fun insertGame(game: List<GameEntity>) = mGameDao.insertDataGame(game)

    fun updateGame(game: GameEntity) = mGameDao.updateDataGame(game)
}