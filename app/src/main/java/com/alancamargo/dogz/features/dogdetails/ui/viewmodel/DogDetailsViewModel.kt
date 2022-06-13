package com.alancamargo.dogz.features.dogdetails.ui.viewmodel

import com.alancamargo.dogz.core.arch.viewmodel.StateViewModel
import com.alancamargo.dogz.features.doglist.ui.model.UiDog

class DogDetailsViewModel(
    dog: UiDog
) : StateViewModel<DogDetailsUiState>(initialState = DogDetailsUiState()) {

    init {
        setState { state -> state.setTemperamentState(dog.temperament.isBlank()) }
        setState { state -> state.setOriginState(dog.origin.isBlank()) }
        setState { state -> state.setWebViewState(dog.wikipediaUrl.isBlank()) }
    }

}
