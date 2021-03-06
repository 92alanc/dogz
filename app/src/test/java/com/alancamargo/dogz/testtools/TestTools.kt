package com.alancamargo.dogz.testtools

import com.alancamargo.dogz.features.doglist.data.mapping.toDb
import com.alancamargo.dogz.features.doglist.data.model.DbDog
import com.alancamargo.dogz.features.doglist.data.model.DogResponse
import com.alancamargo.dogz.features.doglist.data.model.ImageResponse
import com.alancamargo.dogz.features.doglist.domain.mapping.toUi
import com.alancamargo.dogz.features.doglist.domain.model.Dog
import com.alancamargo.dogz.features.doglist.ui.model.UiDog

fun stubDogList() = listOf(
    Dog(
        id = "123",
        breed = "Staffordshire Terrier",
        imageUrl = "https://test.com/staffy.png",
        temperament = "Fun and loving. Will bite you to bits to show affection",
        origin = "Usually around corner shops",
        wikipediaUrl = "https://fakepedia.com/staffy"
    ),
    Dog(
        id = "456",
        breed = "Pinscher",
        imageUrl = "https://test.com/pinscher.png",
        temperament = "Very cocky, especially around huge dogs that could easily tear them apart",
        origin = "Gated communities, inside posh women's purses, etc.",
        wikipediaUrl = "https://fakepedia.com/pinscher"
    )
)

fun stubDogResponseList() = listOf(
    DogResponse(
        id = 123L,
        breed = "Staffordshire Terrier",
        image = ImageResponse(url = "https://test.com/staffy.png"),
        temperament = "Fun and loving. Will bite you to bits to show affection",
        origin = "Usually around corner shops",
        wikipediaUrl = "https://fakepedia.com/staffy"
    ),
    DogResponse(
        id = 456L,
        breed = "Pinscher",
        image = ImageResponse(url = "https://test.com/pinscher.png"),
        temperament = "Very cocky, especially around huge dogs that could easily tear them apart",
        origin = "Gated communities, inside posh women's purses, etc.",
        wikipediaUrl = "https://fakepedia.com/pinscher"
    )
)

fun stubUiDogList() = stubDogList().map { it.toUi() }

fun stubDbDogList() = stubDogList().map { it.toDb() }

fun stubUiDog() = UiDog(
    id = "456",
    breed = "Pinscher",
    imageUrl = "https://test.com/pinscher.png",
    temperament = "Very cocky, especially around huge dogs that could easily tear them apart",
    origin = "Gated communities, inside posh women's purses, etc.",
    wikipediaUrl = "https://fakepedia.com/pinscher"
)

fun stubDbDog() = DbDog(
    id = "456",
    breed = "Pinscher",
    imageUrl = "https://test.com/pinscher.png",
    temperament = "Very cocky, especially around huge dogs that could easily tear them apart",
    origin = "Gated communities, inside posh women's purses, etc.",
    wikipediaUrl = "https://fakepedia.com/pinscher"
)
