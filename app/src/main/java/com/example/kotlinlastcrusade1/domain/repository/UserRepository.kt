package com.example.kotlinlastcrusade1.domain.repository

import com.example.kotlinlastcrusade1.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>

    suspend fun getUserDetails(username: String): User
}