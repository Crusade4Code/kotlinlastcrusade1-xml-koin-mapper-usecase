package com.example.kotlinlastcrusade1.repository

import com.example.kotlinlastcrusade1.core.coroutines.dispatchers.TestDispatchersProvider
import com.example.kotlinlastcrusade1.data.model.UserDto
import com.example.kotlinlastcrusade1.data.remote.GitHubApi
import com.example.kotlinlastcrusade1.data.repository.UserRepositoryImpl
import com.example.kotlinlastcrusade1.domain.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.toList
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
    private val testScheduler = TestCoroutineScheduler() // Create shared scheduler
    private val testDispatcher = StandardTestDispatcher(testScheduler) // Use shared scheduler
    private val testDispatchersProvider = TestDispatchersProvider(testDispatcher) // Use TestDispatchersProvider

    @Before
    fun setUp() {
        repository = UserRepositoryImpl(api, testDispatchersProvider)
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
        val expectedUsers = listOf(
            User(id = 1, login = "User1", bio = "Bio1", avatarUrl = "url1"),
            User(id = 2, login = "User2", bio = "Bio2", avatarUrl = "url2"),
        )

        // Configure mock to return a list of DTOs
        `when`(api.getUsers()).thenReturn(apiResponse)

        // Get users as Flow
        val resultFlow = repository.getUsersFlow()

        // Collect the result from the Flow
        val result = resultFlow.toList() // Collects all emitted values

        verify(api).getUsers() // Verify that the API was called

        assertEquals(expectedUsers, result.first()) // Compare with the expected result
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

        // Configure mock to return DTOs
        `when`(api.getUserDetails(username)).thenReturn(apiResponse)

        // Get user details as Flow
        val resultFlow = repository.getUserDetailsFlow(username)

        // Collect the result from the Flow
        val result = resultFlow.toList() // Collects all emitted values

        // Verify the API call
        verify(api).getUserDetails(username)

        // Assert that the first value emitted matches expected user
        assertEquals(expectedRepos, result.first())
    }
}
