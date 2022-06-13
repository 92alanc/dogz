package com.alancamargo.lystchallenge.features.doglist.data.remote

import com.alancamargo.lystchallenge.features.doglist.data.api.DogService
import com.alancamargo.lystchallenge.testtools.stubDogList
import com.alancamargo.lystchallenge.testtools.stubDogResponseList
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException
import kotlin.time.ExperimentalTime

@ExperimentalTime
class DogRemoteDataSourceImplTest {

    private val mockService = mockk<DogService>()
    private val remoteDataSource = DogRemoteDataSourceImpl(mockService)

    @Test
    fun `when service returns dogs getDogs should return dogs as domain`() = runBlocking {
        // Given
        coEvery { mockService.getDogs() } returns stubDogResponseList()

        // When
        val actual = remoteDataSource.getDogs()

        // Then
        val expected = stubDogList()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when service throws exception getDogs should throw exception`() {
        // Given
        val message = "Someone on the backend did an oopsie"
        coEvery { mockService.getDogs() } throws IOException(message)

        // Then
        try {
            runBlocking { remoteDataSource.getDogs() }
        } catch (exception: IOException) {
            assertThat(exception).isInstanceOf(IOException::class.java)
            assertThat(exception).hasMessageThat().isEqualTo(message)
        }
    }

}
