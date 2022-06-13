package com.alancamargo.lystchallenge.features.doglist.data.local

import com.alancamargo.lystchallenge.features.doglist.data.db.DogDao
import com.alancamargo.lystchallenge.features.doglist.data.mapping.toDb
import com.alancamargo.lystchallenge.features.doglist.data.mapping.toDomain
import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DogLocalDataSourceImpl(private val database: DogDao) : DogLocalDataSource {

    override suspend fun getDogs(): List<Dog> {
        return database.getDogs().map { it.toDomain() }
    }

    override suspend fun saveDogs(dogs: List<Dog>) {
        dogs.forEach { dog ->
            val dbDog = database.getDog(dog.id)
            if (dbDog != null) {
                database.updateDog(dog.toDb())
            } else {
                database.insertDog(dog.toDb())
            }
        }
    }

    override fun clearCache(): Flow<Unit> = flow {
        val task = database.deleteAllDogs()
        emit(task)
    }

}
