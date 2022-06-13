package com.alancamargo.lystchallenge.features.doglist.ui.viewmodel

import com.alancamargo.lystchallenge.core.arch.viewmodel.UiAction
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog

sealed class DogListUiAction : UiAction {

    object ShowLoading : DogListUiAction()

    object HideLoading : DogListUiAction()

    object ShowError : DogListUiAction()

    data class OpenDogDetails(val dog: UiDog) : DogListUiAction()

    object NotifyCacheCleared : DogListUiAction()

    object ShowClearCacheError : DogListUiAction()

}
