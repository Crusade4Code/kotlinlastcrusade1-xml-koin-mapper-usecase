package com.example.kotlinlastcrusade1.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kotlinlastcrusade1.domain.model.Repo
import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.domain.usecase.GetUserDetailsUseCase
import com.example.kotlinlastcrusade1.domain.usecase.GetUserReposUseCase
import com.example.kotlinlastcrusade1.ui.viewmodel.UserViewModel
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

    private val testDispatcher = StandardTestDispatcher() // Use a test dispatcher

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher) // Sets the test dispatcher as Main

        userViewModel = UserViewModel(getUserDetailsUseCase, getUserReposUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Restore the original Main dispatcher after testing
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
        advanceUntilIdle() // Simulate coroutine waiting

        // Assert
        assertEquals(Pair(mockUser, mockRepos), userViewModel.allDataReceived.value)
        coVerify { getUserDetailsUseCase(username) }
        coVerify { getUserReposUseCase(username) }
    }
}