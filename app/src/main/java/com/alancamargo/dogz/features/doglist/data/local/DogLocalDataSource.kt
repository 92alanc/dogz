package com.alancamargo.dogz.features.doglist.data.local

import com.alancamargo.dogz.features.doglist.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogLocalDataSource {

    suspend fun getDogs(): List<Dog>

    suspend fun saveDogs(dogs: List<Dog>)

    fun clearCache(): Flow<Unit>

}
