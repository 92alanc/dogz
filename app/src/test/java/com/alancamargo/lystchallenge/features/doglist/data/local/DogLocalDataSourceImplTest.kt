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
        val message = "Not my fault, I'm just the bearer of the bad news"
        coEvery { mockDatabase.getDogs() } throws IllegalStateException(message)

        // When
        val result = localDataSource.getDogs()

        // Then
        result.test {
            val exception = awaitError()
            assertThat(exception).isInstanceOf(IllegalStateException::class.java)
            assertThat(exception).hasMessageThat().isEqualTo(message)
        }
    }

    @Test
    fun `with existing dogs when database succeeds saveDogs should update dogs`() = runBlocking {
        // Given
        coEvery { mockDatabase.getDog(id = any()) } returns stubDbDog()

        // When
        val dogs = stubDogList()
        val itemCount = dogs.size
        val result = localDataSource.saveDogs(dogs)

        // Then
        coVerify(exactly = itemCount) { mockDatabase.getDog(id = any()) }
        coVerify(exactly = itemCount) { mockDatabase.updateDog(dog = any()) }
        coVerify(exactly = 0) { mockDatabase.insertDog(dog = any()) }

        result.test {
            awaitItem()
            awaitComplete()
        }
    }

    @Test
    fun `with non existing dogs when database succeeds saveDogs should insert dogs`() = runBlocking {
        // Given
        coEvery { mockDatabase.getDog(id = any()) } returns null

        // When
        val dogs = stubDogList()
        val itemCount = dogs.size
        val result = localDataSource.saveDogs(dogs)

        // Then
        coVerify(exactly = itemCount) { mockDatabase.getDog(id = any()) }
        coVerify(exactly = itemCount) { mockDatabase.insertDog(dog = any()) }
        coVerify(exactly = 0) { mockDatabase.updateDog(dog = any()) }

        result.test {
            awaitItem()
            awaitComplete()
        }
    }

    @Test
    fun `when database throws exception saveDogs should return error`() = runBlocking {
        // Given
        val message = "This database sucks"
        coEvery { mockDatabase.getDog(id = any()) } throws IllegalArgumentException(message)

        // When
        val dogs = stubDogList()
        val itemCount = dogs.size
        val result = localDataSource.saveDogs(dogs)

        // Then
        coVerify(exactly = itemCount) { mockDatabase.getDog(id = any()) }
        coVerify(exactly = 0) { mockDatabase.insertDog(dog = any()) }
        coVerify(exactly = 0) { mockDatabase.updateDog(dog = any()) }

        result.test {
            val exception = awaitError()
            assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
            assertThat(exception).hasMessageThat().isEqualTo(message)
        }
    }

    @Test
    fun `when database succeeds deleteAllDogs should delete all dogs`() = runBlocking {
        // When
        val result = localDataSource.deleteAllDogs()

        // Then
        coVerify { mockDatabase.deleteAllDogs() }

        result.test {
            awaitItem()
            awaitComplete()
        }
    }

    @Test
    fun `when database throws exception deleteAllDogs should return error`() = runBlocking {
        // Given
        val message = "Might as well use a pen and paper as a database next time"
        coEvery { mockDatabase.deleteAllDogs() } throws IOException(message)

        // When
        val result = localDataSource.deleteAllDogs()

        // Then
        result.test {
            val exception = awaitError()
            assertThat(exception).isInstanceOf(IOException::class.java)
            assertThat(exception).hasMessageThat().isEqualTo(message)
        }
    }

}
