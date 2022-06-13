package com.alancamargo.dogz.features.doglist.data.remote

import com.alancamargo.dogz.features.doglist.domain.model.Dog

interface DogRemoteDataSource {

    suspend fun getDogs(): List<Dog>

}
