package com.example.kotlinlastcrusade1.usecase

import com.example.kotlinlastcrusade1.domain.model.Repo
import com.example.kotlinlastcrusade1.domain.repository.RepoRepository
import com.example.kotlinlastcrusade1.domain.usecase.GetUserReposUseCase
import io.mockk.coEvery
import io.mockk.coVerify
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
class GetUserDtoReposUseCaseTest {

    private lateinit var getUserReposUseCase: GetUserReposUseCase
    private val mockRepository: RepoRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Define test dispatcher as Main
        getUserReposUseCase = GetUserReposUseCase(mockRepository)
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
        val mockRepos = listOf(
            Repo(1, "repo1", "repo_url1", "descricao repo1"),
            Repo(1, "repo2", "repo_url2", null),
        )

        // Configure the repository mock to return a Flow of repos
        coEvery { mockRepository.getUsersReposFlow(username) } returns flow { emit(mockRepos) }

        // Act
        val resultFlow = getUserReposUseCase(username) // Call the use case which returns a Flow

        // Collect the result from the Flow
        val result = resultFlow.toList() // Collect all emitted values

        // Assert
        assertEquals(mockRepos, result.first()) // Compare with the expected result
        coVerify { mockRepository.getUsersReposFlow(username) } // Verify the repository call
    }
}