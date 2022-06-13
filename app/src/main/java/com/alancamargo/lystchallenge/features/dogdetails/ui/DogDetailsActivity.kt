package com.alancamargo.lystchallenge.features.dogdetails.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.alancamargo.lystchallenge.core.tools.args
import com.alancamargo.lystchallenge.core.tools.createIntent
import com.alancamargo.lystchallenge.core.tools.observeState
import com.alancamargo.lystchallenge.core.tools.putArguments
import com.alancamargo.lystchallenge.databinding.ActivityDogDetailsBinding
import com.alancamargo.lystchallenge.features.dogdetails.ui.viewmodel.DogDetailsUiState
import com.alancamargo.lystchallenge.features.dogdetails.ui.viewmodel.DogDetailsViewModel
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog
import kotlinx.parcelize.Parcelize
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DogDetailsActivity : AppCompatActivity() {

    private var _binding: ActivityDogDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by args<Args>()
    private val viewModel by viewModel<DogDetailsViewModel> { parametersOf(args.dog) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDogDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        observeState(viewModel, ::onStateChanged)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setUpUi() {
        binding.imgPhoto.load(args.dog.imageUrl)
        setUpToolbar()
        setUpTexts()
        setUpWebView()
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            title = args.dog.breed
        }
    }

    private fun setUpTexts() = with(binding) {
        txtTemperament.text = args.dog.temperament
        txtOrigin.text = args.dog.origin
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView() = with(binding.webView) {
        settings.javaScriptEnabled = true
        loadUrl(args.dog.wikipediaUrl)
    }

    private fun onStateChanged(state: DogDetailsUiState) = with(binding) {
        temperamentGroup.isVisible = state.showTemperament
        originGroup.isVisible = state.showOrigin
        webView.isVisible = state.showWebView
    }

    @Parcelize
    data class Args(val dog: UiDog) : Parcelable

    companion object {
        fun getIntent(context: Context, args: Args): Intent {
            return context.createIntent(DogDetailsActivity::class).putArguments(args)
        }
    }

}
