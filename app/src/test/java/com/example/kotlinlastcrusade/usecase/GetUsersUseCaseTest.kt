package com.example.kotlinlastcrusade.usecase

import com.example.kotlinlastcrusade.domain.model.User
import com.example.kotlinlastcrusade.domain.repository.UserRepository
import com.example.kotlinlastcrusade.domain.usecase.GetUsersUseCase
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
class GetUsersUseCaseTest {

    private lateinit var getUsersUseCase: GetUsersUseCase
    private val mockRepository: UserRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Define o dispatcher de teste como Main
        getUsersUseCase = GetUsersUseCase(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Restaura o Main dispatcher original após o teste
        testDispatcher.cancel()
    }

    @Test
    fun `invoke should return list of users`() = runTest {
        // Arrange
        val mockUsers = listOf(User("user1", 1, "bio1", "avatar_url1"))
        coEvery { mockRepository.getUsers() } returns mockUsers

        // Act
        val result = getUsersUseCase()

        // Assert
        assertEquals(mockUsers, result)
    }
}