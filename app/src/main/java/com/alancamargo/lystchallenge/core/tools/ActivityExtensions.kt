package com.alancamargo.lystchallenge.core.tools

import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.lystchallenge.core.arch.viewmodel.UiAction
import com.alancamargo.lystchallenge.core.arch.viewmodel.UiState
import com.alancamargo.lystchallenge.core.arch.viewmodel.ViewModel

fun <S : UiState, A : UiAction> AppCompatActivity.observeState(
    viewModel: ViewModel<S, A>,
    onStateChanged: (S) -> Unit
) {
    viewModel.state.observe(this) { state ->
        onStateChanged(state)
    }
}

fun <S : UiState, A : UiAction> AppCompatActivity.observeAction(
    viewModel: ViewModel<S, A>,
    onAction: (A) -> Unit
) {
    viewModel.action.observe(this) { action ->
        onAction(action)
    }
}
