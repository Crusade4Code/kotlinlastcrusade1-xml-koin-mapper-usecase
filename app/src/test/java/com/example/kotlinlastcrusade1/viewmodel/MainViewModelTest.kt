package com.example.kotlinlastcrusade1.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.domain.usecase.GetUsersUseCase
import com.example.kotlinlastcrusade1.ui.viewmodel.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
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
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    // Configure a rule for LiveData to be executed instantly
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Use case mock
    private lateinit var getUsersUseCase: GetUsersUseCase

    // ViewModel to be tested
    private lateinit var viewModel: MainViewModel

    // Mock observer to verify changes on LiveData
    @RelaxedMockK
    private lateinit var observer: Observer<List<User>>

    private val testDispatcher = StandardTestDispatcher() // Uses a test dispatcher

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher) // Define test dispatcher as Main

        // Initiate mocks
        MockKAnnotations.init(this)

        // Create a mock of use case
        getUsersUseCase = mockk(relaxed = true)

        // Initiate ViewModel with the mock of use case
        viewModel = MainViewModel(getUsersUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset original main dispatcher after the test
        testDispatcher.cancel()
    }

    @Test
    fun `fetches users list and updates LiveData`() = runTest {
        // Given that the use case returns a fictitious list of users
        val usersList = listOf(
            User(login = "maria", id = 1, bio = "Dev", avatarUrl = "https://example.com/avatar1"),
            User(login = "joao", id = 2, bio = "Designer", avatarUrl = "https://example.com/avatar2")
        )
        coEvery { getUsersUseCase() } returns usersList

        // Adds an observer to the ViewModel's LiveData
        viewModel.users.observeForever(observer)

        // When the function to search users is called
        viewModel.getUsers()

        // Advances the test dispatcher to ensure all coroutines complete
        testDispatcher.scheduler.advanceUntilIdle()

        // Then check if LiveData is updated with the correct user list
        verify { observer.onChanged(usersList) }
        assertEquals(usersList, viewModel.users.value)
    }
}