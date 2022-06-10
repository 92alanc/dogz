package com.alancamargo.lystchallenge.features.doglist.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alancamargo.lystchallenge.features.doglist.data.model.DbDog

@Dao
interface DogDao {

    @Query("SELECT * FROM DOGS ORDER BY breed")
    suspend fun getDogs(): List<DbDog>

    @Query("SELECT * FROM DOGS WHERE id = :id LIMIT 1")
    suspend fun getDog(id: String): DbDog?

    @Insert(entity = DbDog::class)
    suspend fun insertDog(dog: DbDog)

    @Update(entity = DbDog::class)
    suspend fun updateDog(dog: DbDog)

    @Query("DELETE FROM DOGS")
    suspend fun deleteAllDogs()

}
