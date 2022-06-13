package com.alancamargo.lystchallenge.features.doglist.data.mapping

import com.alancamargo.lystchallenge.features.doglist.data.model.DbDog
import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog

fun Dog.toDb() = DbDog(
    id = id,
    breed = breed,
    imageUrl = imageUrl,
    temperament = temperament,
    origin = origin,
    wikipediaUrl = wikipediaUrl
)
