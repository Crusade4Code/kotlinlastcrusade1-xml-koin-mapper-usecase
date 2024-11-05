package com.example.kotlinlastcrusade.domain.usecase

import com.example.kotlinlastcrusade.domain.model.Repo
import com.example.kotlinlastcrusade.domain.repository.RepoRepository

class GetUserReposUseCase(private val repository: RepoRepository) {
    suspend operator fun invoke(username: String): List<Repo> {
        return repository.getUserRepos(username)
    }
}