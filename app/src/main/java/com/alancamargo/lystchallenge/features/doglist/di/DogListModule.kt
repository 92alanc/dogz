package com.alancamargo.lystchallenge.features.doglist.di

import com.alancamargo.lystchallenge.core.arch.di.FeatureModule
import com.alancamargo.lystchallenge.core.db.DatabaseProvider
import com.alancamargo.lystchallenge.core.network.HttpClient
import com.alancamargo.lystchallenge.features.doglist.data.api.DogService
import com.alancamargo.lystchallenge.features.doglist.data.local.DogLocalDataSource
import com.alancamargo.lystchallenge.features.doglist.data.local.DogLocalDataSourceImpl
import com.alancamargo.lystchallenge.features.doglist.data.remote.DogRemoteDataSource
import com.alancamargo.lystchallenge.features.doglist.data.remote.DogRemoteDataSourceImpl
import com.alancamargo.lystchallenge.features.doglist.domain.usecase.GetDogsUseCase
import org.koin.dsl.module

class DogListModule : FeatureModule() {

    override val domain = module {
        factory { GetDogsUseCase(repository = get()) }
    }

    override val data = module {
        factory { get<HttpClient>().getService(DogService::class.java) }
        factory { get<DatabaseProvider>().provideDogDao() }
        factory<DogRemoteDataSource> { DogRemoteDataSourceImpl(service = get()) }
        factory<DogLocalDataSource> { DogLocalDataSourceImpl(database = get()) }
    }

}
