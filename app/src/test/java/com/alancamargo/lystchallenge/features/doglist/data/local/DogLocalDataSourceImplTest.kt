package com.alancamargo.lystchallenge.features.doglist.data.local

import app.cash.turbine.test
import com.alancamargo.lystchallenge.features.doglist.data.db.DogDao
import com.alancamargo.lystchallenge.testtools.stubDbDog
import com.alancamargo.lystchallenge.testtools.stubDbDogList
import com.alancamargo.lystchallenge.testtools.stubDogList
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException
import kotlin.time.ExperimentalTime

@ExperimentalTime
class DogLocalDataSourceImplTest {

    private val mockDatabase = mockk<DogDao>(relaxed = true)
    private val localDataSource = DogLocalDataSourceImpl(mockDatabase)

    @Test
    fun `when database returns dogs getDogs should return dogs as domain`() = runBlocking {
        // Given
        coEvery { mockDatabase.getDogs() } returns stubDbDogList()

        // When
        val result = localDataSource.getDogs()

        // Then
        val expected = stubDogList()
        result.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when database throws exception getDogs should return error`() = runBlocking {
        // Given
        val message = "This database sucks"
        coEvery { mockDatabase.getDogs() } throws IOException(message)

        // When
        val result = localDataSource.getDogs()

        // Then
        result.test {
            val exception = awaitError()
            assertThat(exception).isInstanceOf(IOException::class.java)
            assertThat(exception).hasMessageThat().isEqualTo(message)
        }
    }

    @Test
    fun `with existing dogs saveDogs should update on database`() = runBlocking {
        // Given
        coEvery { mockDatabase.getDog(id = any()) } returns stubDbDog()
        val dogs = stubDogList()

        // When
        val result = localDataSource.saveDogs(dogs)

        // Then
        result.test {
            awaitItem()
            awaitComplete()
        }

        coVerify(exactly = dogs.size) { mockDatabase.getDog(id = any()) }
        coVerify(exactly = dogs.size) { mockDatabase.updateDog(dog = any()) }
        coVerify(exactly = 0) { mockDatabase.insertDog(dog = any()) }
    }

    @Test
    fun `with non existing dogs saveDogs should insert on database`() = runBlocking {
        // Given
        coEvery { mockDatabase.getDog(id = any()) } returns null
        val dogs = stubDogList()

        // When
        val result = localDataSource.saveDogs(dogs)

        // Then
        result.test {
            awaitItem()
            awaitComplete()
        }

        coVerify(exactly = dogs.size) { mockDatabase.getDog(id = any()) }
        coVerify(exactly = dogs.size) { mockDatabase.insertDog(dog = any()) }
        coVerify(exactly = 0) { mockDatabase.updateDog(dog = any()) }
    }

    @Test
    fun `when database throws exception saveDogs should return error`() = runBlocking {
        // Given
        val message = "Don't even look at me that way, I'm just the bearer of the bad news"
        coEvery { mockDatabase.getDog(id = any()) } throws IllegalStateException(message)
        val dogs = stubDogList()

        // When
        val result = localDataSource.saveDogs(dogs)

        // Then
        result.test {
            val exception = awaitError()
            assertThat(exception).isInstanceOf(IllegalStateException::class.java)
            assertThat(exception).hasMessageThat().isEqualTo(message)
        }
    }

    @Test
    fun `clearCache should delete all dogs from database`() = runBlocking {
        // When
        val result = localDataSource.clearCache()

        // Then
        result.test {
            awaitItem()
            awaitComplete()
        }

        coVerify { mockDatabase.deleteAllDogs() }
    }

    @Test
    fun `when database throws exception clearCache should return error`() = runBlocking {
        // Given
        val message = "You're not getting rid of these dogs anytime soon mate"
        coEvery { mockDatabase.deleteAllDogs() } throws IllegalArgumentException(message)

        // When
        val result = localDataSource.clearCache()

        // Then
        result.test {
            val exception = awaitError()
            assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
            assertThat(exception).hasMessageThat().isEqualTo(message)
        }
    }

}
