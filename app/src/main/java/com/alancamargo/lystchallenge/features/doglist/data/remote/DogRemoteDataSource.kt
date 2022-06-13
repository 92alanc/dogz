package com.alancamargo.lystchallenge.features.doglist.data.remote

import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog

interface DogRemoteDataSource {

    suspend fun getDogs(): List<Dog>

}
