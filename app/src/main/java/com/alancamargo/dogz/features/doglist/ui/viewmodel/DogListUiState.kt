package com.alancamargo.dogz.features.doglist.ui.viewmodel

import com.alancamargo.dogz.core.arch.viewmodel.UiState
import com.alancamargo.dogz.features.doglist.ui.model.UiDog

data class DogListUiState(val dogs: List<UiDog>? = null) : UiState {

    fun onDogsReceived(dogs: List<UiDog>) = copy(dogs = dogs)

}
