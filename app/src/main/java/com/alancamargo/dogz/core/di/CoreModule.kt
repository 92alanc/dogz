package com.alancamargo.dogz.core.di

import androidx.room.Room
import com.alancamargo.dogz.core.arch.di.FeatureModule
import com.alancamargo.dogz.core.db.DatabaseProvider
import com.alancamargo.dogz.core.network.HttpClient
import com.alancamargo.dogz.core.tools.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class CoreModule : FeatureModule() {

    override val data = module {
        single {
            Room.databaseBuilder(
                androidContext(),
                DatabaseProvider::class.java,
                "database"
            ).fallbackToDestructiveMigration().build()
        }

        factory { HttpClient() }
        factory<ErrorLogger> { ErrorLoggerImpl() }
    }

    override val ui = module {
        factory<ToastHelper> { ToastHelperImpl(context = get()) }
        factory<DialogueHelper> { DialogueHelperImpl(context = get()) }
    }

}
