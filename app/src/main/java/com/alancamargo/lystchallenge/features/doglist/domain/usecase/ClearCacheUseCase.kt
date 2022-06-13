package com.alancamargo.lystchallenge.features.doglist.domain.usecase

import com.alancamargo.lystchallenge.features.doglist.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow

class ClearCacheUseCase(private val repository: DogRepository) {

    operator fun invoke(): Flow<Unit> = repository.clearCache()

}
