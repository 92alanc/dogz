package com.alancamargo.lystchallenge.features.dogdetails.di

import com.alancamargo.lystchallenge.core.arch.di.FeatureModule
import com.alancamargo.lystchallenge.features.dogdetails.ui.navigation.DogDetailsActivityNavigationImpl
import com.alancamargo.lystchallenge.navigation.DogDetailsActivityNavigation
import org.koin.dsl.module

class DogDetailsModule : FeatureModule() {

    override val ui = module {
        factory<DogDetailsActivityNavigation> { DogDetailsActivityNavigationImpl() }
    }

}
