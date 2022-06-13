package com.alancamargo.dogz.features.doglist.domain.usecase

import app.cash.turbine.test
import com.alancamargo.dogz.features.doglist.domain.repository.DogRepository
import com.alancamargo.dogz.testtools.stubDogList
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GetDogsUseCaseTest {

    private val mockRepository = mockk<DogRepository>()
    private val useCase = GetDogsUseCase(mockRepository)

    @Test
    fun `invoke should return dogs from repository`() = runBlocking {
        // Given
        val expected = stubDogList()
        every { mockRepository.getDogs() } returns flowOf(expected)

        // When
        val result = useCase()

        // Then
        result.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
            awaitComplete()
        }
    }

}
