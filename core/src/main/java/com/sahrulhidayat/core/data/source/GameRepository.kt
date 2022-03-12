package com.sahrulhidayat.core.data.source

import com.sahrulhidayat.core.data.source.local.LocalDataSource
import com.sahrulhidayat.core.data.source.remote.RemoteDataSource
import com.sahrulhidayat.core.data.source.remote.network.ApiResponse
import com.sahrulhidayat.core.data.source.remote.response.GameResponse
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.domain.repository.IGameRepository
import com.sahrulhidayat.core.utils.AppExecutors
import com.sahrulhidayat.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {
    override fun getAllGames(sort: String): Flow<Resource<List<GameModel>>> {
        return object : NetworkBoundResource<List<GameModel>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<GameModel>> {
                return localDataSource.getAllGames(sort).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<GameModel>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> {
                return remoteDataSource.getGames()
            }

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapGameResponseToEntities(data)
                localDataSource.insertGame(gameList)
            }
        }.asLiveData()
    }

    override fun getAllFavoriteGames(): Flow<List<GameModel>> {
        return localDataSource.getAllFavoriteGames().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getSearchedGames(name: String): Flow<List<GameModel>> {
        return localDataSource.getSearchedGames(name).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: GameModel, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntities(game)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteGame(gameEntity, state)
        }
    }
}