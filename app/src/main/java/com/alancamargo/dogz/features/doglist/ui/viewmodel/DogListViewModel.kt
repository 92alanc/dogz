package com.alancamargo.dogz.features.doglist.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.alancamargo.dogz.core.arch.viewmodel.ViewModel
import com.alancamargo.dogz.core.tools.ErrorLogger
import com.alancamargo.dogz.features.doglist.domain.mapping.toUi
import com.alancamargo.dogz.features.doglist.domain.usecase.ClearCacheUseCase
import com.alancamargo.dogz.features.doglist.domain.usecase.GetDogsUseCase
import com.alancamargo.dogz.features.doglist.ui.model.UiDog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DogListViewModel(
    private val getDogsUseCase: GetDogsUseCase,
    private val clearCacheUseCase: ClearCacheUseCase,
    private val errorLogger: ErrorLogger,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel<DogListUiState, DogListUiAction>(initialState = DogListUiState()) {

    fun loadDogs() {
        viewModelScope.launch {
            getDogsUseCase().flowOn(dispatcher).onStart {
                sendAction { DogListUiAction.ShowLoading }
            }.catch { throwable ->
                errorLogger.log(throwable)
                sendAction { DogListUiAction.ShowError }
            }.onCompletion {
                sendAction { DogListUiAction.HideLoading }
            }.collect { domainDogs ->
                val dogs = domainDogs.map { it.toUi() }
                setState { state -> state.onDogsReceived(dogs) }
            }
        }
    }

    fun onDogClicked(dog: UiDog) {
        sendAction { DogListUiAction.OpenDogDetails(dog) }
    }

    fun onClearCacheClicked() {
        viewModelScope.launch {
            clearCacheUseCase().flowOn(dispatcher).catch { throwable ->
                errorLogger.log(throwable)
                sendAction { DogListUiAction.ShowClearCacheError }
            }.collect {
                sendAction { DogListUiAction.NotifyCacheCleared }
            }
        }
    }

}
