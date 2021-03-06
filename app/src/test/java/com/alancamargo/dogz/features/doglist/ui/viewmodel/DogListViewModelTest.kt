package com.alancamargo.dogz.features.doglist.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.alancamargo.dogz.core.tools.ErrorLogger
import com.alancamargo.dogz.features.doglist.domain.usecase.ClearCacheUseCase
import com.alancamargo.dogz.features.doglist.domain.usecase.GetDogsUseCase
import com.alancamargo.dogz.testtools.stubDogList
import com.alancamargo.dogz.testtools.stubUiDog
import com.alancamargo.dogz.testtools.stubUiDogList
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.SocketException

@ExperimentalCoroutinesApi
class DogListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockGetDogsUseCase = mockk<GetDogsUseCase>()
    private val mockClearCacheUseCase = mockk<ClearCacheUseCase>()
    private val mockErrorLogger = mockk<ErrorLogger>(relaxed = true)
    private val scheduler = TestCoroutineScheduler()

    private val mockStateObserver = mockk<Observer<DogListUiState>>(relaxed = true)
    private val mockActionObserver = mockk<Observer<DogListUiAction>>(relaxed = true)

    private lateinit var viewModel: DogListViewModel

    @Before
    fun setUp() {
        val testDispatcher = StandardTestDispatcher(scheduler)
        Dispatchers.setMain(testDispatcher)

        viewModel = DogListViewModel(
            mockGetDogsUseCase,
            mockClearCacheUseCase,
            mockErrorLogger,
            testDispatcher
        ).apply {
            state.observeForever(mockStateObserver)
            action.observeForever(mockActionObserver)
        }
    }

    @Test
    fun `loadDogs should send ShowLoading action`() {
        // Given
        every { mockGetDogsUseCase() } returns flowOf(stubDogList())

        // When
        viewModel.loadDogs()

        // Then
        scheduler.advanceTimeBy(delayTimeMillis = 10)
        verify { mockActionObserver.onChanged(DogListUiAction.ShowLoading) }
    }

    @Test
    fun `when use case returns dogs loadDogs should set state with dogs`() {
        // Given
        every { mockGetDogsUseCase() } returns flowOf(stubDogList())

        // When
        viewModel.loadDogs()

        // Then
        val expected = stubUiDogList()
        scheduler.advanceTimeBy(delayTimeMillis = 10)
        verify { mockStateObserver.onChanged(DogListUiState(expected)) }
    }

    @Test
    fun `when use case returns dogs loadDogs should send HideLoading action`() {
        // Given
        every { mockGetDogsUseCase() } returns flowOf(stubDogList())

        // When
        viewModel.loadDogs()

        // Then
        scheduler.advanceTimeBy(delayTimeMillis = 10)
        verify { mockActionObserver.onChanged(DogListUiAction.HideLoading) }
    }

    @Test
    fun `when use case returns error loadDogs should log error`() {
        // Given
        every { mockGetDogsUseCase() } returns flow { throw SocketException("Status code: 200") }

        // When
        viewModel.loadDogs()

        // Then
        scheduler.advanceTimeBy(delayTimeMillis = 10)
        verify { mockErrorLogger.log(any<SocketException>()) }
    }

    @Test
    fun `when use case returns error loadDogs should send ShowError action`() {
        // Given
        every { mockGetDogsUseCase() } returns flow { throw Throwable() }

        // When
        viewModel.loadDogs()

        // Then
        scheduler.advanceTimeBy(delayTimeMillis = 10)
        verify { mockActionObserver.onChanged(DogListUiAction.ShowError) }
    }

    @Test
    fun `when use case returns error loadDogs should send HideLoading action`() {
        // Given
        every { mockGetDogsUseCase() } returns flow { throw Throwable() }

        // When
        viewModel.loadDogs()

        // Then
        scheduler.advanceTimeBy(delayTimeMillis = 10)
        verify { mockActionObserver.onChanged(DogListUiAction.HideLoading) }
    }

    @Test
    fun `onDogClicked should send OpenDogDetails action`() {
        // Given
        val expected = stubUiDog()

        // When
        viewModel.onDogClicked(expected)

        // Then
        verify { mockActionObserver.onChanged(DogListUiAction.OpenDogDetails(expected)) }
    }

    @Test
    fun `when cache is cleared onClearCacheClicked should send NotifyCacheCleared action`() {
        // Given
        every { mockClearCacheUseCase() } returns flowOf(Unit)

        // When
        viewModel.onClearCacheClicked()

        // Then
        scheduler.advanceTimeBy(delayTimeMillis = 10)
        verify { mockActionObserver.onChanged(DogListUiAction.NotifyCacheCleared) }
    }

    @Test
    fun `when cache is not cleared onClearCacheClicked should send ShowClearCacheError action`() {
        // Given
        every { mockClearCacheUseCase() } returns flow { throw Throwable() }

        // When
        viewModel.onClearCacheClicked()

        // Then
        scheduler.advanceTimeBy(delayTimeMillis = 10)
        verify { mockActionObserver.onChanged(DogListUiAction.ShowClearCacheError) }
    }

    @Test
    fun `when cache is not cleared onClearCacheClicked should log error`() {
        // Given
        every { mockClearCacheUseCase() } returns flow { throw IllegalStateException() }

        // When
        viewModel.onClearCacheClicked()

        // Then
        scheduler.advanceTimeBy(delayTimeMillis = 10)
        verify { mockErrorLogger.log(any<IllegalStateException>()) }
    }

}
