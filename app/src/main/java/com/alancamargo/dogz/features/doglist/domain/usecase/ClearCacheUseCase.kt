package com.alancamargo.dogz.features.doglist.domain.usecase

import com.alancamargo.dogz.features.doglist.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow

class ClearCacheUseCase(private val repository: DogRepository) {

    operator fun invoke(): Flow<Unit> = repository.clearCache()

}
