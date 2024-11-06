package com.example.kotlinlastcrusade1.data.repository

import com.example.kotlinlastcrusade1.data.remote.GitHubApi
import com.example.kotlinlastcrusade1.domain.mapper.RepoMapper.toDomainList
import com.example.kotlinlastcrusade1.domain.model.Repo
import com.example.kotlinlastcrusade1.domain.repository.RepoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoRepositoryImpl(
    private val api: GitHubApi,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) : RepoRepository {
    override suspend fun getUserRepos(username: String): List<Repo> =
        withContext(defaultDispatcher) { api.getUserRepos(username).toDomainList() }
}