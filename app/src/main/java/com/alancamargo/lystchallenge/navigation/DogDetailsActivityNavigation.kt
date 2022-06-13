package com.alancamargo.lystchallenge.navigation

import android.content.Context
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog

interface DogDetailsActivityNavigation {

    fun startActivity(context: Context, dog: UiDog)

}
