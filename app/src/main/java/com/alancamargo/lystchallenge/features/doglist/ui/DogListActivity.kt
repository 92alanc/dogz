package com.alancamargo.lystchallenge.features.doglist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.lystchallenge.R
import com.alancamargo.lystchallenge.core.tools.observeAction
import com.alancamargo.lystchallenge.core.tools.observeState
import com.alancamargo.lystchallenge.databinding.ActivityDogListBinding
import com.alancamargo.lystchallenge.features.doglist.ui.adapter.DogAdapter
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog
import com.alancamargo.lystchallenge.features.doglist.ui.viewmodel.DogListUiAction
import com.alancamargo.lystchallenge.features.doglist.ui.viewmodel.DogListUiState
import com.alancamargo.lystchallenge.features.doglist.ui.viewmodel.DogListViewModel
import com.alancamargo.lystchallenge.navigation.DogDetailsActivityNavigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class DogListActivity : AppCompatActivity() {

    private var _binding: ActivityDogListBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { DogAdapter(viewModel::onDogClicked) }
    private val viewModel by viewModel<DogListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpUi()
        observeViewModel()
        viewModel.loadDogs()
    }

    private fun setUpUi() {
        with(binding) {
            recyclerView.adapter = adapter
            swipeRefreshLayout.setOnRefreshListener {
                swipeRefreshLayout.isRefreshing = false
                viewModel.loadDogs()
            }
        }
    }

    private fun observeViewModel() {
        observeState(viewModel, ::onStateChanged)
        observeAction(viewModel, ::onAction)
    }

    private fun onStateChanged(state: DogListUiState) {
        state.dogs?.let(adapter::submitList)
    }

    private fun onAction(action: DogListUiAction) {
        when (action) {
            is DogListUiAction.ShowLoading -> binding.swipeRefreshLayout.isRefreshing = true
            is DogListUiAction.HideLoading -> binding.swipeRefreshLayout.isRefreshing = false
            is DogListUiAction.ShowError -> showError()
            is DogListUiAction.OpenDogDetails -> openDogDetails(action.dog)
        }
    }

    private fun showError() {
        MaterialAlertDialogBuilder(this).setTitle(R.string.error_title)
            .setMessage(R.string.error_message)
            .setIcon(R.drawable.ic_error)
            .setNeutralButton(R.string.ok, null)
            .show()
    }

    private fun openDogDetails(dog: UiDog) {
        val navigation = get<DogDetailsActivityNavigation>()
        navigation.startActivity(context = this, dog = dog)
    }

}
