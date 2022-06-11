package com.alancamargo.lystchallenge.features.doglist.domain.mapping

import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog

fun Dog.toUi() = UiDog(
    id = id,
    breed = breed,
    imageUrl = imageUrl,
    temperament = temperament,
    origin = origin,
    wikipediaUrl = wikipediaUrl
)
