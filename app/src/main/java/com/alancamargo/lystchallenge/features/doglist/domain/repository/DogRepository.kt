package com.alancamargo.lystchallenge.features.doglist.domain.repository

import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogRepository {

    fun getDogs(): Flow<List<Dog>>

}
