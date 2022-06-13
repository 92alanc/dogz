package com.alancamargo.dogz.features.doglist.domain.usecase

import app.cash.turbine.test
import com.alancamargo.dogz.features.doglist.domain.repository.DogRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class ClearCacheUseCaseTest {

    private val mockRepository = mockk<DogRepository>()
    private val useCase = ClearCacheUseCase(mockRepository)

    @Test
    fun `invoke should clear cache on repository`() = runBlocking {
        // Given
        every { mockRepository.clearCache() } returns flowOf(Unit)

        // When
        val result = useCase()

        // Then
        result.test {
            awaitItem()
            awaitComplete()
        }
    }

}
