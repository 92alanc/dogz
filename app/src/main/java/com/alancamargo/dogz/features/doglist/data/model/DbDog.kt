package com.alancamargo.dogz.features.doglist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dogs")
data class DbDog(
    @PrimaryKey val id: String,
    val breed: String,
    val imageUrl: String,
    val temperament: String,
    val origin: String,
    val wikipediaUrl: String
)
