package com.example.kotlinlastcrusade.repository

import com.example.kotlinlastcrusade.data.model.UserDto
import com.example.kotlinlastcrusade.data.remote.GitHubApi
import com.example.kotlinlastcrusade.data.repository.UserRepositoryImpl
import com.example.kotlinlastcrusade.domain.model.User
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
class UserRepositoryImplTest {

    private lateinit var repository: UserRepositoryImpl
    private val api: GitHubApi = mock(GitHubApi::class.java)
    private val testScheduler = TestCoroutineScheduler() // Cria um scheduler compartilhado
    private val testDispatcher = StandardTestDispatcher(testScheduler) // Usa o scheduler compartilhado

    @Before
    fun setUp() {
        repository = UserRepositoryImpl(api, testDispatcher)
    }

    @After
    fun tearDown() {
        testDispatcher.cancel()
    }

    @Test
    fun `getUsers should return mapped list of users`() = runTest(testScheduler) {
        val apiResponse = listOf(
            UserDto(id = 1, login = "User1", bio = "Bio1", avatarUrl = "url1"),
            UserDto(id = 2, login = "User2", bio = "Bio2", avatarUrl = "url2"),
        )
        val expectedRepos = listOf(
            User(id = 1, login = "User1", bio = "Bio1", avatarUrl = "url1"),
            User(id = 2, login = "User2", bio = "Bio2", avatarUrl = "url2"),
        )

        // Configura o mock para retornar uma lista de DTOs
        `when`(api.getUsers()).thenReturn(apiResponse)

        val result = repository.getUsers()

        verify(api).getUsers()

        assertEquals(expectedRepos, result)
    }

    @Test
    fun `getUserDetails should return user`() = runTest(testScheduler) {
        val username = "testUser"
        val apiResponse = UserDto(
            id = 1,
            login = "User1",
            bio = "Bio1",
            avatarUrl = "url1",
        )
        val expectedRepos = User(
            id = 1,
            login = "User1",
            bio = "Bio1",
            avatarUrl = "url1",
        )

        // Configura o mock para retornar uma lista de DTOs
        `when`(api.getUserDetails(username)).thenReturn(apiResponse)

        val result = repository.getUserDetails(username)

        verify(api).getUserDetails(username)

        assertEquals(expectedRepos, result)
    }
}
