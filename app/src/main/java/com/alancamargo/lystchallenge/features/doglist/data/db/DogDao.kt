package com.alancamargo.lystchallenge.features.doglist.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alancamargo.lystchallenge.features.doglist.data.model.DbDog

@Dao
interface DogDao {

    @Query("SELECT * FROM DOGS ORDER BY BREED")
    suspend fun getDogs(): List<DbDog>

    @Query("SELECT * FROM DOGS WHERE ID = :id LIMIT 1")
    suspend fun getDog(id: String): DbDog?

    @Insert
    suspend fun insertDog(dog: DbDog)

    @Update
    suspend fun updateDog(dog: DbDog)

    @Query("DELETE FROM DOGS")
    suspend fun deleteAllDogs()

}