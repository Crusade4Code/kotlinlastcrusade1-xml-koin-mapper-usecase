package com.example.kotlinlastcrusade1.domain.usecase

import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(private val repository: UserRepository) {
    operator fun invoke(): Flow<List<User>> {
        return repository.getUsersFlow()
    }
}