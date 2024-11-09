package com.example.kotlinlastcrusade1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlastcrusade1.domain.model.Repo
import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.domain.usecase.GetUserDetailsUseCase
import com.example.kotlinlastcrusade1.domain.usecase.GetUserReposUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class UserDataState(
    val user: User? = null,
    val repos: List<Repo>? = null,
    val isLoading: Boolean = true // Indicates whether data is still loading
)

class UserViewModel(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getUserReposUseCase: GetUserReposUseCase
) : ViewModel() {
    // StateFlow to keep screen state
    private val _userDataState = MutableStateFlow(UserDataState(null, null))
    val userDataState: StateFlow<UserDataState> = _userDataState.asStateFlow()

    // Function to start observing user details and repos
    fun observeUserData(username: String) {
        viewModelScope.launch {
            // Initially set loading to true
            _userDataState.value = UserDataState(isLoading = true)

            // Combine user details and repos flows
            val userDetailsFlow = getUserDetailsUseCase(username)
            val userReposFlow = getUserReposUseCase(username)

            // Combine both flows
            combine(userDetailsFlow, userReposFlow) { userDetails, userRepos ->
                UserDataState(user = userDetails, repos = userRepos, isLoading = false)
            }.collect { dataState ->
                // Update the user data state with the collected data
                _userDataState.value = dataState
            }
        }
    }
}