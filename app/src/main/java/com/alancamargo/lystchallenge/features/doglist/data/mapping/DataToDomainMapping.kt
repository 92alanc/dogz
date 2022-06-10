package com.alancamargo.lystchallenge.features.doglist.data.mapping

import com.alancamargo.lystchallenge.features.doglist.data.model.DbDog
import com.alancamargo.lystchallenge.features.doglist.data.model.DogResponse
import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog

fun DogResponse.toDomain() = Dog(
    id = id.toString(),
    breed = breed,
    imageUrl = image.url,
    temperament = temperament,
    origin = origin,
    wikipediaUrl = wikipediaUrl
)

fun DbDog.toDomain() = Dog(
    id = id,
    breed = breed,
    imageUrl = imageUrl,
    temperament = temperament,
    origin = origin,
    wikipediaUrl = wikipediaUrl
)
