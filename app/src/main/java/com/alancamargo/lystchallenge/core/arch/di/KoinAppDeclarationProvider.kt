package com.alancamargo.lystchallenge.core.arch.di

import android.app.Application
import com.alancamargo.lystchallenge.core.di.CoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.KoinAppDeclaration

object KoinAppDeclarationProvider {

    fun provideAppDeclaration(app: Application): KoinAppDeclaration = {
        androidContext(app)
        CoreModule().load()
    }

}
