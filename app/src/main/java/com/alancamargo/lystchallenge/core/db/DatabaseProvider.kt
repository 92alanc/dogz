package com.alancamargo.lystchallenge.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alancamargo.lystchallenge.features.doglist.data.db.DogDao
import com.alancamargo.lystchallenge.features.doglist.data.model.DbDog

@Database(
    entities = [DbDog::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseProvider : RoomDatabase() {

    abstract fun provideDogDao(): DogDao

}
