package com.example.kotlinlastcrusade1.data.repository

import com.example.kotlinlastcrusade1.core.coroutines.dispatchers.base.DispatchersProvider
import com.example.kotlinlastcrusade1.data.remote.GitHubApi
import com.example.kotlinlastcrusade1.domain.mapper.UserMapper.toDomain
import com.example.kotlinlastcrusade1.domain.mapper.UserMapper.toDomainList
import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val api: GitHubApi,
    private val dispatchersProvider: DispatchersProvider,
) : UserRepository {
    // Private method to get users
    private suspend fun getUsers(): List<User> =
        withContext(dispatchersProvider.default) { api.getUsers().toDomainList() }

    // New method to return Flow with all users
    override fun getUsersFlow(): Flow<List<User>> = flow {
        // Emits the list of users obtained from API
        val userList = getUsers()
        emit(userList)
    }

    // Private method to get user details from the API
    private suspend fun fetchUserDetails(username: String): User =
        withContext(dispatchersProvider.default) { api.getUserDetails(username).toDomain() }

    // Public method to return user details as Flow
    override fun getUserDetailsFlow(username: String): Flow<User> = flow {
        // Emit the user details retrieved from the API
        val userDetails = fetchUserDetails(username) // Call the private method
        emit(userDetails) // Emit the user details
    }
}