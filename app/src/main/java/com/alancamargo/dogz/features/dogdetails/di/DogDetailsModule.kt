package com.alancamargo.dogz.features.dogdetails.di

import com.alancamargo.dogz.core.arch.di.FeatureModule
import com.alancamargo.dogz.features.dogdetails.ui.navigation.DogDetailsActivityNavigationImpl
import com.alancamargo.dogz.features.dogdetails.ui.viewmodel.DogDetailsViewModel
import com.alancamargo.dogz.features.doglist.ui.model.UiDog
import com.alancamargo.dogz.navigation.DogDetailsActivityNavigation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class DogDetailsModule : FeatureModule() {

    override val ui = module {
        factory<DogDetailsActivityNavigation> { DogDetailsActivityNavigationImpl() }
        viewModel { (dog: UiDog) -> DogDetailsViewModel(dog) }
    }

}
