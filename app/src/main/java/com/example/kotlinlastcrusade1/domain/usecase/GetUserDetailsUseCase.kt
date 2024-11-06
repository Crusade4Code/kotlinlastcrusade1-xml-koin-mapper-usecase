package com.example.kotlinlastcrusade1.domain.usecase

import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.domain.repository.UserRepository

class GetUserDetailsUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(username: String): User {
        return repository.getUserDetails(username)
    }
}