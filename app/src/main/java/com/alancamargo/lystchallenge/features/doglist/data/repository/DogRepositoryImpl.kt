package com.alancamargo.lystchallenge.features.doglist.data.repository

import com.alancamargo.lystchallenge.features.doglist.data.local.DogLocalDataSource
import com.alancamargo.lystchallenge.features.doglist.data.remote.DogRemoteDataSource
import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog
import com.alancamargo.lystchallenge.features.doglist.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DogRepositoryImpl(
    private val remoteDataSource: DogRemoteDataSource,
    private val localDataSource: DogLocalDataSource
) : DogRepository {

    override fun getDogs(): Flow<List<Dog>> = flow {
        val dogs = try {
            remoteDataSource.getDogs().also { dogs ->
                localDataSource.saveDogs(dogs)
            }
        } catch (exception: Throwable) {
            localDataSource.getDogs()
        }

        emit(dogs)
    }

    override fun clearCache(): Flow<Unit> = localDataSource.clearCache()

}
