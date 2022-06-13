package com.alancamargo.dogz.features.doglist.di

import com.alancamargo.dogz.core.arch.di.FeatureModule
import com.alancamargo.dogz.core.db.DatabaseProvider
import com.alancamargo.dogz.core.network.HttpClient
import com.alancamargo.dogz.features.doglist.data.api.DogService
import com.alancamargo.dogz.features.doglist.data.local.DogLocalDataSource
import com.alancamargo.dogz.features.doglist.data.local.DogLocalDataSourceImpl
import com.alancamargo.dogz.features.doglist.data.remote.DogRemoteDataSource
import com.alancamargo.dogz.features.doglist.data.remote.DogRemoteDataSourceImpl
import com.alancamargo.dogz.features.doglist.data.repository.DogRepositoryImpl
import com.alancamargo.dogz.features.doglist.domain.repository.DogRepository
import com.alancamargo.dogz.features.doglist.domain.usecase.ClearCacheUseCase
import com.alancamargo.dogz.features.doglist.domain.usecase.GetDogsUseCase
import com.alancamargo.dogz.features.doglist.ui.viewmodel.DogListViewModel
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
        viewModel {
            DogListViewModel(
                getDogsUseCase = get(),
                clearCacheUseCase = get(),
                errorLogger = get()
            )
        }
    }

}
