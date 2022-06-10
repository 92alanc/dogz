package com.alancamargo.lystchallenge.features.doglist.data.remote

import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogRemoteDataSource {

    fun getDogs(): Flow<List<Dog>>

}
