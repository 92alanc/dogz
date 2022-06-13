package com.alancamargo.dogz

import android.app.Application
import com.alancamargo.dogz.di.KoinAppDeclarationProvider
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(KoinAppDeclarationProvider.provideAppDeclaration(app = this))
    }

}
