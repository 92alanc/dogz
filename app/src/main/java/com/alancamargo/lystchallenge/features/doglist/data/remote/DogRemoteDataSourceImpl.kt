package com.alancamargo.lystchallenge.features.doglist.data.remote

import com.alancamargo.lystchallenge.features.doglist.data.api.DogService
import com.alancamargo.lystchallenge.features.doglist.data.mapping.toDomain
import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DogRemoteDataSourceImpl(private val service: DogService) : DogRemoteDataSource {

    override fun getDogs(): Flow<List<Dog>> = flow {
        val dogs = service.getDogs().map { it.toDomain() }
        emit(dogs)
    }

}
