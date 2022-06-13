package com.alancamargo.dogz.navigation

import android.content.Context
import com.alancamargo.dogz.features.doglist.ui.model.UiDog

interface DogDetailsActivityNavigation {

    fun startActivity(context: Context, dog: UiDog)

}
