package com.alancamargo.lystchallenge.features.doglist.data.local

import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogLocalDataSource {

    fun getDogs(): Flow<List<Dog>>

    fun saveDogs(dogs: List<Dog>): Flow<Unit>

    fun deleteAllDogs(): Flow<Unit>

}
