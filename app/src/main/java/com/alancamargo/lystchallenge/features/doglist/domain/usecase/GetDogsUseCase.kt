package com.alancamargo.lystchallenge.features.doglist.domain.usecase

import com.alancamargo.lystchallenge.features.doglist.domain.model.Dog
import com.alancamargo.lystchallenge.features.doglist.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow

class GetDogsUseCase(private val repository: DogRepository) {

    operator fun invoke(): Flow<List<Dog>> = repository.getDogs()

}
