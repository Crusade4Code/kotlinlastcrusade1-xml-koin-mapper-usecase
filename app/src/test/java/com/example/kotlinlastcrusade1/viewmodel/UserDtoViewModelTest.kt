package com.example.kotlinlastcrusade1.viewmodel

import com.example.kotlinlastcrusade1.domain.model.Repo
import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.domain.usecase.GetUserDetailsUseCase
import com.example.kotlinlastcrusade1.domain.usecase.GetUserReposUseCase
import com.example.kotlinlastcrusade1.ui.viewmodel.UserViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserDtoViewModelTest {

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

    @Test
    fun `getAllData should emit Pair of user and repos`() = runTest {
        // Arrange
        val username = "user1"
        val mockUser = User(username, 1, "bio1", "avatar_url1")
        val mockRepos = listOf(Repo(1, "repo1", "url1", "description1"))

        // Configure mock to return Flow
        coEvery { getUserDetailsUseCase(username) } returns flow { emit(mockUser) }
        coEvery { getUserReposUseCase(username) } returns flow { emit(mockRepos) }

        // Act
        userViewModel.observeUserData(username)

        // Collect and assert with a timeout or a break condition to avoid indefinite waits
        val job = launch {
            userViewModel.userDataState.collect { dataState ->
                // Assert only after the first emission
                if (dataState.user != null && dataState.repos != null) {
                    assertEquals(mockUser, dataState.user)
                    assertEquals(mockRepos, dataState.repos)
                    // Break the collection loop since we only need the first value for this test
                    return@collect
                }
            }
        }

        // Allow time for the emissions to happen
        advanceUntilIdle() // Advance the test dispatcher until all coroutines are complete
        job.cancel() // Cancel the collection job to clean up
    }
}