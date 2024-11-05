package com.example.kotlinlastcrusade.domain.usecase

import com.example.kotlinlastcrusade.domain.model.User
import com.example.kotlinlastcrusade.domain.repository.UserRepository

class GetUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}