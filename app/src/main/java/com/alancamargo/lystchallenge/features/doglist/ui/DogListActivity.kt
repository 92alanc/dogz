package com.alancamargo.lystchallenge.features.doglist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.lystchallenge.core.tools.observeAction
import com.alancamargo.lystchallenge.core.tools.observeState
import com.alancamargo.lystchallenge.databinding.ActivityDogListBinding
import com.alancamargo.lystchallenge.features.doglist.ui.adapter.DogAdapter
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog
import com.alancamargo.lystchallenge.features.doglist.ui.viewmodel.DogListUiAction
import com.alancamargo.lystchallenge.features.doglist.ui.viewmodel.DogListUiState
import com.alancamargo.lystchallenge.features.doglist.ui.viewmodel.DogListViewModel
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
        // TODO
    }

    private fun openDogDetails(dog: UiDog) {
        // TODO
        Toast.makeText(this, dog.breed, Toast.LENGTH_SHORT).show()
    }

}
