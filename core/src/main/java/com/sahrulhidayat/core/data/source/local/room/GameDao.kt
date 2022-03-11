package com.sahrulhidayat.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.sahrulhidayat.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface GameDao {
    @RawQuery(observedEntities = [GameEntity::class])
    fun getAllGames(query: SupportSQLiteQuery): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataGame(games: List<GameEntity>)

    @Update
    fun updateDataGame(game: GameEntity)

    @Query("SELECT * FROM game_entities WHERE isFavorite = 1")
    fun getAllFavoriteGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game_entities WHERE name like :name")
    fun getSearchResult(name: String): Flow<List<GameEntity>>
}