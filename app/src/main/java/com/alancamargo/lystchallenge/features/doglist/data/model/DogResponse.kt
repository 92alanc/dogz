package com.alancamargo.lystchallenge.features.doglist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogResponse(
    @SerialName("id") val id: Long,
    @SerialName("name") val breed: String,
    @SerialName("image") val image: ImageResponse,
    @SerialName("temperament") val temperament: String? = null,
    @SerialName("origin") val origin: String? = null,
    @SerialName("wikipedia_url") val wikipediaUrl: String? = null
)
