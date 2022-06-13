package com.alancamargo.lystchallenge.di

import android.app.Application
import com.alancamargo.lystchallenge.core.di.CoreModule
import com.alancamargo.lystchallenge.features.dogdetails.di.DogDetailsModule
import com.alancamargo.lystchallenge.features.doglist.di.DogListModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.KoinAppDeclaration

object KoinAppDeclarationProvider {

    fun provideAppDeclaration(app: Application): KoinAppDeclaration = {
        androidContext(app)
        CoreModule().load()
        DogListModule().load()
        DogDetailsModule().load()
    }

}
