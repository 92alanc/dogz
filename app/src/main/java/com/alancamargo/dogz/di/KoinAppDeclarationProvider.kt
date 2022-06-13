package com.alancamargo.dogz.di

import android.app.Application
import com.alancamargo.dogz.core.di.CoreModule
import com.alancamargo.dogz.features.dogdetails.di.DogDetailsModule
import com.alancamargo.dogz.features.doglist.di.DogListModule
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
