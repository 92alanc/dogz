package com.alancamargo.lystchallenge.features.doglist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(@SerialName("url") val url: String)