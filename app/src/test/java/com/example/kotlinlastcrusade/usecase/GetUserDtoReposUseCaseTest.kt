package com.example.kotlinlastcrusade.usecase

import com.example.kotlinlastcrusade.domain.model.Repo
import com.example.kotlinlastcrusade.domain.repository.RepoRepository
import com.example.kotlinlastcrusade.domain.usecase.GetUserReposUseCase
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
class GetUserDtoReposUseCaseTest {

    private lateinit var getUserReposUseCase: GetUserReposUseCase
    private val mockRepository: RepoRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Define o dispatcher de teste como Main
        getUserReposUseCase = GetUserReposUseCase(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Restaura o Main dispatcher original após o teste
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
        coEvery { mockRepository.getUserRepos(username) } returns mockRepos

        // Act
        val result = getUserReposUseCase(username)

        // Assert
        assertEquals(mockRepos, result)
    }
}