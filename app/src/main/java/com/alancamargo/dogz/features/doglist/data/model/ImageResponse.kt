package com.alancamargo.dogz.features.doglist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(@SerialName("url") val url: String)
