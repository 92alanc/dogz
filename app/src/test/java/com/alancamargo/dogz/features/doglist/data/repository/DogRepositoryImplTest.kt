package com.alancamargo.dogz.features.doglist.data.repository

import app.cash.turbine.test
import com.alancamargo.dogz.features.doglist.data.local.DogLocalDataSource
import com.alancamargo.dogz.features.doglist.data.remote.DogRemoteDataSource
import com.alancamargo.dogz.testtools.stubDogList
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class DogRepositoryImplTest {

    private val mockRemoteDataSource = mockk<DogRemoteDataSource>()
    private val mockLocalDataSource = mockk<DogLocalDataSource>(relaxed = true)
    private val repository = DogRepositoryImpl(mockRemoteDataSource, mockLocalDataSource)

    @Test
    fun `when remote data source returns dogs getDogs should return dogs`() = runBlocking {
        // Given
        val expected = stubDogList()
        coEvery { mockRemoteDataSource.getDogs() } returns expected

        // When
        val result = repository.getDogs()

        // Then
        result.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
            awaitComplete()
        }

        coVerify(exactly = 0) { mockLocalDataSource.getDogs() }
    }

    @Test
    fun `when remote data source returns dogs getDogs should save dogs on local data source`() = runBlocking {
        // Given
        val expected = stubDogList()
        coEvery { mockRemoteDataSource.getDogs() } returns expected

        // When
        val result = repository.getDogs()

        // Then
        result.test {
            awaitItem()
            awaitComplete()
        }

        coVerify { mockLocalDataSource.saveDogs(expected) }
    }

    @Test
    fun `when remote data source throws exception getDogs should return from local data source`() = runBlocking {
        // Given
        val expected = stubDogList()
        coEvery { mockRemoteDataSource.getDogs() } throws Throwable()
        coEvery { mockLocalDataSource.getDogs() } returns expected

        // When
        val result = repository.getDogs()

        // Then
        result.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when both remote and local data sources throw exception getDogs should return error`() = runBlocking {
        // Given
        val message = "This is an error in case you're wondering"
        coEvery { mockRemoteDataSource.getDogs() } throws IllegalArgumentException(message)
        coEvery { mockLocalDataSource.getDogs() } throws NullPointerException(message)

        // When
        val result = repository.getDogs()

        // Then
        result.test {
            val exception = awaitError()
            assertThat(exception).isInstanceOf(NullPointerException::class.java)
            assertThat(exception).hasMessageThat().isEqualTo(message)
        }
    }

    @Test
    fun `clearCache should clear cache on local data source`() {
        // When
        repository.clearCache()

        // Then
        coVerify { mockLocalDataSource.clearCache() }
    }

}
