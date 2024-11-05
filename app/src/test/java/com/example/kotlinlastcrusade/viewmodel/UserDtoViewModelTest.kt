package com.example.kotlinlastcrusade.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kotlinlastcrusade.domain.model.Repo
import com.example.kotlinlastcrusade.domain.model.User
import com.example.kotlinlastcrusade.domain.usecase.GetUserDetailsUseCase
import com.example.kotlinlastcrusade.domain.usecase.GetUserReposUseCase
import com.example.kotlinlastcrusade.ui.viewmodel.UserViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserDtoViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userViewModel: UserViewModel
    private val getUserDetailsUseCase: GetUserDetailsUseCase = mockk()
    private val getUserReposUseCase: GetUserReposUseCase = mockk()

    private val testDispatcher = StandardTestDispatcher() // Usa um dispatcher de teste

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher) // Define o dispatcher de teste como o Main

        userViewModel = UserViewModel(getUserDetailsUseCase, getUserReposUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Restaura o Main dispatcher original após o teste
        testDispatcher.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getAllData should post Pair of user and repos`() = runTest {
        // Arrange
        val username = "user1"
        val mockUser = User(username, 1, "bio1", "avatar_url1")
        val mockRepos = listOf(Repo(1, "repo1", "url1", "description1"))

        coEvery { getUserDetailsUseCase(username) } returns mockUser
        coEvery { getUserReposUseCase(username) } returns mockRepos

        // Act
        userViewModel.getAllData(username)
        advanceUntilIdle() // Simule a espera do coroutine

        // Assert
        assertEquals(Pair(mockUser, mockRepos), userViewModel.allDataReceived.value)
        coVerify { getUserDetailsUseCase(username) }
        coVerify { getUserReposUseCase(username) }
    }
}