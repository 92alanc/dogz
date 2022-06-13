package com.alancamargo.lystchallenge.features.doglist.data.remote

import com.alancamargo.lystchallenge.features.doglist.data.api.DogService
import com.alancamargo.lystchallenge.features.doglist.data.mapping.toDomain
import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog

class DogRemoteDataSourceImpl(private val service: DogService) : DogRemoteDataSource {

    override suspend fun getDogs(): List<Dog> {
        return service.getDogs().map { it.toDomain() }
    }

}
