package com.alancamargo.lystchallenge.features.doglist.data.local

import com.alancamargo.lystchallenge.features.doglist.data.db.DogDao
import com.alancamargo.lystchallenge.features.doglist.data.mapping.toDb
import com.alancamargo.lystchallenge.features.doglist.data.mapping.toDomain
import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DogLocalDataSourceImpl(private val database: DogDao) : DogLocalDataSource {

    override fun getDogs(): Flow<List<Dog>> = flow {
        val dogs = database.getDogs().map { it.toDomain() }
        emit(dogs)
    }

    override fun saveDogs(dogs: List<Dog>): Flow<Unit> = flow {
        dogs.forEach { dog ->
            val dogExists = database.getDog(dog.id) != null

            val task = if (dogExists) {
                database.updateDog(dog.toDb())
            } else {
                database.insertDog(dog.toDb())
            }

            emit(task)
        }
    }

    override fun deleteAllDogs(): Flow<Unit> = flow {
        emit(database.deleteAllDogs())
    }

}
