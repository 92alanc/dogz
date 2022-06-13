package com.alancamargo.dogz.features.doglist.data.remote

import com.alancamargo.dogz.features.doglist.data.api.DogService
import com.alancamargo.dogz.features.doglist.data.mapping.toDomain
import com.alancamargo.dogz.features.doglist.domain.model.Dog

class DogRemoteDataSourceImpl(private val service: DogService) : DogRemoteDataSource {

    override suspend fun getDogs(): List<Dog> {
        return service.getDogs().map { it.toDomain() }
    }

}
