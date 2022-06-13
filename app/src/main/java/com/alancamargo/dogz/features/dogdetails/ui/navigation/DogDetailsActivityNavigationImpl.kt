package com.alancamargo.dogz.features.dogdetails.ui.navigation

import android.content.Context
import com.alancamargo.dogz.features.dogdetails.ui.DogDetailsActivity
import com.alancamargo.dogz.features.doglist.ui.model.UiDog
import com.alancamargo.dogz.navigation.DogDetailsActivityNavigation

class DogDetailsActivityNavigationImpl : DogDetailsActivityNavigation {

    override fun startActivity(context: Context, dog: UiDog) {
        val args = DogDetailsActivity.Args(dog)
        val intent = DogDetailsActivity.getIntent(context, args)
        context.startActivity(intent)
    }

}
