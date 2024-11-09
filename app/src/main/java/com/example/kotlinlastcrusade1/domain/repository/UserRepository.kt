package com.example.kotlinlastcrusade1.domain.repository

import com.example.kotlinlastcrusade1.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsersFlow(): Flow<List<User>>
    fun getUserDetailsFlow(username: String): Flow<User>
}