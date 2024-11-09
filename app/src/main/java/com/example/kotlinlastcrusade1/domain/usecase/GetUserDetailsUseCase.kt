package com.example.kotlinlastcrusade1.domain.usecase

import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserDetailsUseCase(private val repository: UserRepository) {
    operator fun invoke(username: String): Flow<User> {
        return repository.getUserDetailsFlow(username) // Return the Flow from the repository
    }
}