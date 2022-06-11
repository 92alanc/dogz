package com.alancamargo.lystchallenge.features.doglist.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiDog(
    val id: String,
    val breed: String,
    val imageUrl: String,
    val temperament: String,
    val origin: String,
    val wikipediaUrl: String
) : Parcelable
