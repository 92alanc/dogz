package com.alancamargo.lystchallenge.features.dogdetails.di

import com.alancamargo.lystchallenge.core.arch.di.FeatureModule
import com.alancamargo.lystchallenge.features.dogdetails.ui.navigation.DogDetailsActivityNavigationImpl
import com.alancamargo.lystchallenge.features.dogdetails.ui.viewmodel.DogDetailsViewModel
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog
import com.alancamargo.lystchallenge.navigation.DogDetailsActivityNavigation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class DogDetailsModule : FeatureModule() {

    override val ui = module {
        factory<DogDetailsActivityNavigation> { DogDetailsActivityNavigationImpl() }
        viewModel { (dog: UiDog) -> DogDetailsViewModel(dog) }
    }

}
