package com.alancamargo.lystchallenge.features.dogdetails.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog
import com.alancamargo.lystchallenge.testtools.stubUiDog
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class DogDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockStateObserver = mockk<Observer<DogDetailsUiState>>(relaxed = true)

    private lateinit var viewModel: DogDetailsViewModel

    @Test
    fun `when temperament is not blank temperament texts should be visible`() {
        // Given
        val dog = stubUiDog().copy(origin = "", wikipediaUrl = "")

        // When
        createViewModel(dog)

        // Then
        val expectedState = DogDetailsUiState(showTemperament = true)
        verify { mockStateObserver.onChanged(expectedState) }
    }

    @Test
    fun `when temperament is blank temperament texts should not be visible`() {
        // Given
        val dog = stubUiDog().copy(temperament = "", origin = "", wikipediaUrl = "")

        // When
        createViewModel(dog)

        // Then
        val expectedState = DogDetailsUiState(showTemperament = false)
        verify { mockStateObserver.onChanged(expectedState) }
    }

    @Test
    fun `when origin is not blank origin texts should be visible`() {
        // Given
        val dog = stubUiDog().copy(temperament = "", wikipediaUrl = "")

        // When
        createViewModel(dog)

        // Then
        val expectedState = DogDetailsUiState(showOrigin = true)
        verify { mockStateObserver.onChanged(expectedState) }
    }

    @Test
    fun `when origin is blank origin texts should not be visible`() {
        // Given
        val dog = stubUiDog().copy(temperament = "", origin = "", wikipediaUrl = "")

        // When
        createViewModel(dog)

        // Then
        val expectedState = DogDetailsUiState(showOrigin = false)
        verify { mockStateObserver.onChanged(expectedState) }
    }

    @Test
    fun `when wikipedia url is not blank web view should be visible`() {
        // Given
        val dog = stubUiDog().copy(temperament = "", origin = "")

        // When
        createViewModel(dog)

        // Then
        val expectedState = DogDetailsUiState(showWebView = true)
        verify { mockStateObserver.onChanged(expectedState) }
    }

    @Test
    fun `when wikipedia url is blank web view should not be visible`() {
        // Given
        val dog = stubUiDog().copy(temperament = "", origin = "", wikipediaUrl = "")

        // When
        createViewModel(dog)

        // Then
        val expectedState = DogDetailsUiState(showWebView = false)
        verify { mockStateObserver.onChanged(expectedState) }
    }

    private fun createViewModel(dog: UiDog) {
        viewModel = DogDetailsViewModel(dog).apply {
            state.observeForever(mockStateObserver)
        }
    }

}
