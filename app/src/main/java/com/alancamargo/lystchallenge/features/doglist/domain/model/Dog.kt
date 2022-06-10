package com.alancamargo.lystchallenge.features.doglist.domain.model

data class Dog(
    val id: String,
    val breed: String,
    val imageUrl: String,
    val temperament: String,
    val origin: String,
    val wikipediaUrl: String
)
