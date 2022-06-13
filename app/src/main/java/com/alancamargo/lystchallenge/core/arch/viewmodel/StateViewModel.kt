package com.alancamargo.lystchallenge.core.arch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class StateViewModel<S : UiState>(initialState: S) : ViewModel() {

    private val stateLiveData = MutableLiveData(initialState)

    val state: LiveData<S> = stateLiveData

    protected fun setState(block: (S) -> S) {
        stateLiveData.value = block(stateLiveData.value!!)
    }

}
