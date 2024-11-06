package com.example.kotlinlastcrusade1.domain.usecase

import com.example.kotlinlastcrusade1.domain.model.Repo
import com.example.kotlinlastcrusade1.domain.repository.RepoRepository

class GetUserReposUseCase(private val repository: RepoRepository) {
    suspend operator fun invoke(username: String): List<Repo> {
        return repository.getUserRepos(username)
    }
}