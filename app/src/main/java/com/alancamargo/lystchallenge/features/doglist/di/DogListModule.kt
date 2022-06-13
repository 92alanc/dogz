package com.alancamargo.lystchallenge.features.doglist.di

import com.alancamargo.lystchallenge.core.arch.di.FeatureModule
import com.alancamargo.lystchallenge.core.db.DatabaseProvider
import com.alancamargo.lystchallenge.core.network.HttpClient
import com.alancamargo.lystchallenge.features.doglist.data.api.DogService
import com.alancamargo.lystchallenge.features.doglist.data.local.DogLocalDataSource
import com.alancamargo.lystchallenge.features.doglist.data.local.DogLocalDataSourceImpl
import com.alancamargo.lystchallenge.features.doglist.data.remote.DogRemoteDataSource
import com.alancamargo.lystchallenge.features.doglist.data.remote.DogRemoteDataSourceImpl
import com.alancamargo.lystchallenge.features.doglist.data.repository.DogRepositoryImpl
import com.alancamargo.lystchallenge.features.doglist.domain.repository.DogRepository
import com.alancamargo.lystchallenge.features.doglist.domain.usecase.ClearCacheUseCase
import com.alancamargo.lystchallenge.features.doglist.domain.usecase.GetDogsUseCase
import com.alancamargo.lystchallenge.features.doglist.ui.viewmodel.DogListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class DogListModule : FeatureModule() {

    override val domain = module {
        factory { GetDogsUseCase(repository = get()) }
        factory { ClearCacheUseCase(repository = get()) }
        factory<DogRepository> {
            DogRepositoryImpl(
                remoteDataSource = get(),
                localDataSource = get()
            )
        }
    }

    override val data = module {
        factory { get<HttpClient>().getService(DogService::class.java) }
        factory { get<DatabaseProvider>().provideDogDao() }
        factory<DogRemoteDataSource> { DogRemoteDataSourceImpl(service = get()) }
        factory<DogLocalDataSource> { DogLocalDataSourceImpl(database = get()) }
    }

    override val ui = module {
        viewModel { DogListViewModel(getDogsUseCase = get(), errorLogger = get()) }
    }

}
