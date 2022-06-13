package com.alancamargo.dogz.features.dogdetails.ui.viewmodel

import com.alancamargo.dogz.core.arch.viewmodel.UiState

data class DogDetailsUiState(
    val showTemperament: Boolean = false,
    val showOrigin: Boolean = false,
    val showWebView: Boolean = false
) : UiState {

    fun setTemperamentState(
        isTemperamentBlank: Boolean
    ) = copy(showTemperament = !isTemperamentBlank)

    fun setOriginState(isOriginBlank: Boolean) = copy(showOrigin = !isOriginBlank)

    fun setWebViewState(isWikipediaUrlBlank: Boolean) = copy(showWebView = !isWikipediaUrlBlank)

}
