package com.alancamargo.lystchallenge.features.doglist.data.repository

import app.cash.turbine.test
import com.alancamargo.lystchallenge.features.doglist.data.remote.DogRemoteDataSource
import com.alancamargo.lystchallenge.testtools.stubDogList
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class DogRepositoryImplTest {

    private val mockRemoteDataSource = mockk<DogRemoteDataSource>()
    private val repository = DogRepositoryImpl(mockRemoteDataSource)

    @Test
    fun `getDogs should return dogs from remote data source`() = runBlocking {
        // Given
        val expected = stubDogList()
        every { mockRemoteDataSource.getDogs() } returns flowOf(expected)

        // When
        val result = repository.getDogs()

        // Then
        result.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
            awaitComplete()
        }
    }

}
