package com.alancamargo.lystchallenge.features.dogdetails.ui.viewmodel

import com.alancamargo.lystchallenge.core.arch.viewmodel.StateViewModel
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog

class DogDetailsViewModel(
    dog: UiDog
) : StateViewModel<DogDetailsUiState>(initialState = DogDetailsUiState()) {

    init {
        setState { state -> state.setTemperamentState(dog.temperament.isBlank()) }
        setState { state -> state.setOriginState(dog.origin.isBlank()) }
        setState { state -> state.setWebViewState(dog.wikipediaUrl.isBlank()) }
    }

}
