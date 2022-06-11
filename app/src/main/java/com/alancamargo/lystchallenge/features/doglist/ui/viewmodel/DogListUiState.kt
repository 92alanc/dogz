package com.alancamargo.lystchallenge.features.doglist.ui.viewmodel

import com.alancamargo.lystchallenge.core.arch.viewmodel.UiState
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog

data class DogListUiState(val dogs: List<UiDog>? = null) : UiState {

    fun onDogsReceived(dogs: List<UiDog>) = copy(dogs = dogs)

}
