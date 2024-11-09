package com.example.kotlinlastcrusade1.usecase

import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.domain.repository.UserRepository
import com.example.kotlinlastcrusade1.domain.usecase.GetUsersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUsersUseCaseTest {

    private lateinit var getUsersUseCase: GetUsersUseCase
    private val mockRepository: UserRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Define test dispatcher as Main
        getUsersUseCase = GetUsersUseCase(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset original dispatcher after the test
        testDispatcher.cancel()
    }

    @Test
    fun `invoke should return list of users`() = runTest {
        // Arrange
        val mockUsers = listOf(User("user1", 1, "bio1", "avatar_url1"))

        // Configure the repository mock to return a Flow
        coEvery { mockRepository.getUsersFlow() } returns flow { emit(mockUsers) }

        // Act
        val resultFlow = getUsersUseCase() // Call the use case, which returns a Flow

        // Collect the result from the Flow
        val result = resultFlow.toList() // Collects all emitted values

        // Assert
        assertEquals(mockUsers, result.first()) // Compare with the expected result
    }
}