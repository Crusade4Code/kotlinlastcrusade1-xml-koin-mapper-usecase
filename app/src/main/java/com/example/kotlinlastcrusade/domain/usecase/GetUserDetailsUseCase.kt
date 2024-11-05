package com.example.kotlinlastcrusade.domain.usecase

import com.example.kotlinlastcrusade.domain.model.User
import com.example.kotlinlastcrusade.domain.repository.UserRepository

class GetUserDetailsUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(username: String): User {
        return repository.getUserDetails(username)
    }
}