package com.alancamargo.dogz.features.doglist.data.mapping

import com.alancamargo.dogz.features.doglist.data.model.DbDog
import com.alancamargo.dogz.features.doglist.data.model.DogResponse
import com.alancamargo.dogz.features.doglist.domain.model.Dog

fun DogResponse.toDomain() = Dog(
    id = id.toString(),
    breed = breed,
    imageUrl = image.url,
    temperament = temperament.orEmpty(),
    origin = origin.orEmpty(),
    wikipediaUrl = wikipediaUrl.orEmpty()
)

fun DbDog.toDomain() = Dog(
    id = id,
    breed = breed,
    imageUrl = imageUrl,
    temperament = temperament,
    origin = origin,
    wikipediaUrl = wikipediaUrl
)
