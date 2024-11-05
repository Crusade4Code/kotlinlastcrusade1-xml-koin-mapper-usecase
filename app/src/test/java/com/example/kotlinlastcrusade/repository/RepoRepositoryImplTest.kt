package com.example.kotlinlastcrusade.repository

import com.example.kotlinlastcrusade.data.model.RepoDto
import com.example.kotlinlastcrusade.data.remote.GitHubApi
import com.example.kotlinlastcrusade.data.repository.RepoRepositoryImpl
import com.example.kotlinlastcrusade.domain.model.Repo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class RepoRepositoryImplTest {

    private lateinit var repository: RepoRepositoryImpl
    private val api: GitHubApi = mock(GitHubApi::class.java)
    private val testScheduler = TestCoroutineScheduler() // Cria um scheduler compartilhado
    private val testDispatcher = StandardTestDispatcher(testScheduler) // Usa o scheduler compartilhado

    @Before
    fun setUp() {
        repository = RepoRepositoryImpl(api, testDispatcher)
    }

    @After
    fun tearDown() {
        testDispatcher.cancel()
    }

    @Test
    fun `getUserRepos should return mapped list of repos`() = runTest(testScheduler) {
        val username = "testUser"
        val apiResponse = listOf(
            RepoDto(id = 1, name = "Repo1", description = "Repo1", url = "url1"),
            RepoDto(id = 2, name = "Repo2", description = "Repo2", url = "url2")
        )
        val expectedRepos = listOf(
            Repo(id = 1, name = "Repo1", description = "Repo1", url = "url1"),
            Repo(id = 2, name = "Repo2", description = "Repo2", url = "url2")
        )

        // Configura o mock para retornar uma lista de DTOs
        `when`(api.getUserRepos(username)).thenReturn(apiResponse)

        val result = repository.getUserRepos(username)

        verify(api).getUserRepos(username)

        assertEquals(expectedRepos, result)
    }
}
