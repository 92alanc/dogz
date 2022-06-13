package com.alancamargo.dogz.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alancamargo.dogz.features.doglist.data.db.DogDao
import com.alancamargo.dogz.features.doglist.data.model.DbDog

@Database(
    entities = [DbDog::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseProvider : RoomDatabase() {

    abstract fun provideDogDao(): DogDao

}
