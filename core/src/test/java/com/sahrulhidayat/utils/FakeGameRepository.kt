package com.sahrulhidayat.utils

import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.data.source.local.LocalDataSource
import com.sahrulhidayat.core.domain.interfaces.IGameRepository
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.utils.DataMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FakeGameRepository(
    private val localDataSource: LocalDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : IGameRepository {
    override fun getGameList(sort: String): Flow<Resource<List<GameModel>>> {
        return localDataSource.getGameList(sort).map {
            Resource.Success(DataMapper.mapEntitiesToDomain(it))
        }.flowOn(ioDispatcher)
    }

    override fun getGameDetails(id: Int): Flow<Resource<GameModel>> {
        return localDataSource.getGameDetails(id).map {
            Resource.Success(DataMapper.mapDetailsEntityToDomain(it))
        }.flowOn(ioDispatcher)
    }

    override fun getAllFavoriteGames(): Flow<List<GameModel>> {
        return localDataSource.getAllFavoriteGames().map {
            DataMapper.mapEntitiesToDomain(it)
        }.flowOn(ioDispatcher)
    }

    override fun setFavoriteGame(game: GameModel, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        CoroutineScope(ioDispatcher).launch {
            localDataSource.setFavoriteGame(gameEntity, state)
        }
    }
}