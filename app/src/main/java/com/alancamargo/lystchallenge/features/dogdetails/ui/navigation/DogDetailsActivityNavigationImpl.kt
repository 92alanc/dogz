package com.alancamargo.lystchallenge.features.dogdetails.ui.navigation

import android.content.Context
import com.alancamargo.lystchallenge.features.dogdetails.ui.DogDetailsActivity
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog
import com.alancamargo.lystchallenge.navigation.DogDetailsActivityNavigation

class DogDetailsActivityNavigationImpl : DogDetailsActivityNavigation {

    override fun startActivity(context: Context, dog: UiDog) {
        val args = DogDetailsActivity.Args(dog)
        val intent = DogDetailsActivity.getIntent(context, args)
        context.startActivity(intent)
    }

}
