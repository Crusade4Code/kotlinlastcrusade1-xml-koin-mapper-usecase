package com.example.kotlinlastcrusade1.usecase

import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.domain.repository.UserRepository
import com.example.kotlinlastcrusade1.domain.usecase.GetUserDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUserDtoDetailsUseCaseTest {

    private lateinit var getUserDetailsUseCase: GetUserDetailsUseCase
    private val mockRepository: UserRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Define test dispatcher as Main
        getUserDetailsUseCase = GetUserDetailsUseCase(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset original dispatcher after the test
        testDispatcher.cancel()
    }

    @Test
    fun `invoke should return user details`() = runTest {
        // Arrange
        val username = "user1"
        val mockUser = User(username, 1, "bio1", "avatar_url1")
        coEvery { mockRepository.getUserDetails(username) } returns mockUser

        // Act
        val result = getUserDetailsUseCase(username)

        // Assert
        assertEquals(mockUser, result)
    }
}