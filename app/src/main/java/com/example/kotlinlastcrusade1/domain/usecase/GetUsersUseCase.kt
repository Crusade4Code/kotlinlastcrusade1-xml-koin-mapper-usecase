package com.example.kotlinlastcrusade1.domain.usecase

import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.domain.repository.UserRepository

class GetUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}