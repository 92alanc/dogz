package com.alancamargo.lystchallenge

import android.app.Application
import com.alancamargo.lystchallenge.di.KoinAppDeclarationProvider
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(KoinAppDeclarationProvider.provideAppDeclaration(app = this))
    }

}
