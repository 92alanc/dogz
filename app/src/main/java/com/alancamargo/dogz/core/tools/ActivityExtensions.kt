package com.alancamargo.dogz.core.tools

import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.dogz.core.arch.viewmodel.StateViewModel
import com.alancamargo.dogz.core.arch.viewmodel.UiAction
import com.alancamargo.dogz.core.arch.viewmodel.UiState
import com.alancamargo.dogz.core.arch.viewmodel.ViewModel

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

fun <S : UiState> AppCompatActivity.observeState(
    viewModel: StateViewModel<S>,
    onStateChanged: (S) -> Unit
) {
    viewModel.state.observe(this) { state ->
        onStateChanged(state)
    }
}

fun <T : Parcelable> AppCompatActivity.args() = lazy {
    intent.getParcelableExtra(EXTRA_ARGUMENTS) as? T ?: throw IllegalStateException("Missing args!")
}
