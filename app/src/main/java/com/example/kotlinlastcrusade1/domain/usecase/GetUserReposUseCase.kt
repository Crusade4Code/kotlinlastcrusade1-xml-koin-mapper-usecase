package com.example.kotlinlastcrusade1.domain.usecase

import com.example.kotlinlastcrusade1.domain.model.Repo
import com.example.kotlinlastcrusade1.domain.repository.RepoRepository
import kotlinx.coroutines.flow.Flow

class GetUserReposUseCase(private val repository: RepoRepository) {
    operator fun invoke(username: String): Flow<List<Repo>> {
        return repository.getUsersReposFlow(username)
    }
}