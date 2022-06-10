package com.alancamargo.lystchallenge.testtools

import com.alancamargo.lystchallenge.features.doglist.data.model.DbDog
import com.alancamargo.lystchallenge.features.doglist.data.model.DogResponse
import com.alancamargo.lystchallenge.features.doglist.data.model.ImageResponse
import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog

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

fun stubDbDogList() = listOf(
    DbDog(
        id = "123",
        breed = "Staffordshire Terrier",
        imageUrl = "https://test.com/staffy.png",
        temperament = "Fun and loving. Will bite you to bits to show affection",
        origin = "Usually around corner shops",
        wikipediaUrl = "https://fakepedia.com/staffy"
    ),
    DbDog(
        id = "456",
        breed = "Pinscher",
        imageUrl = "https://test.com/pinscher.png",
        temperament = "Very cocky, especially around huge dogs that could easily tear them apart",
        origin = "Gated communities, inside posh women's purses, etc.",
        wikipediaUrl = "https://fakepedia.com/pinscher"
    )
)

fun stubDbDog() = DbDog(
    id = "456",
    breed = "Pinscher",
    imageUrl = "https://test.com/pinscher.png",
    temperament = "Very cocky, especially around huge dogs that could easily tear them apart",
    origin = "Gated communities, inside posh women's purses, etc.",
    wikipediaUrl = "https://fakepedia.com/pinscher"
)

fun stubDog() = Dog(
    id = "456",
    breed = "Pinscher",
    imageUrl = "https://test.com/pinscher.png",
    temperament = "Very cocky, especially around huge dogs that could easily tear them apart",
    origin = "Gated communities, inside posh women's purses, etc.",
    wikipediaUrl = "https://fakepedia.com/pinscher"
)
