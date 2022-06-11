package com.alancamargo.lystchallenge.features.doglist.data.repository

import com.alancamargo.lystchallenge.features.doglist.data.remote.DogRemoteDataSource
import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog
import com.alancamargo.lystchallenge.features.doglist.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow

class DogRepositoryImpl(private val remoteDataSource: DogRemoteDataSource) : DogRepository {

    override fun getDogs(): Flow<List<Dog>> = remoteDataSource.getDogs()

}
