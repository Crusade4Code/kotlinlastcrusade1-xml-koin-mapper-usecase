package com.example.kotlinlastcrusade.domain.repository

import com.example.kotlinlastcrusade.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>

    suspend fun getUserDetails(username: String): User
}