package com.example.kotlinlastcrusade1.data.repository

import com.example.kotlinlastcrusade1.data.remote.GitHubApi
import com.example.kotlinlastcrusade1.domain.mapper.UserMapper.toDomain
import com.example.kotlinlastcrusade1.domain.mapper.UserMapper.toDomainList
import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val api: GitHubApi,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) : UserRepository {
    override suspend fun getUsers(): List<User> = withContext(defaultDispatcher) { api.getUsers().toDomainList() }

    override suspend fun getUserDetails(username: String): User =
        withContext(defaultDispatcher) { api.getUserDetails(username).toDomain() }
}