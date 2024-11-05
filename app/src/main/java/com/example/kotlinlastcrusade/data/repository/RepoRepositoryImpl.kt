package com.example.kotlinlastcrusade.data.repository

import com.example.kotlinlastcrusade.data.remote.GitHubApi
import com.example.kotlinlastcrusade.domain.mapper.RepoMapper.toDomainList
import com.example.kotlinlastcrusade.domain.model.Repo
import com.example.kotlinlastcrusade.domain.repository.RepoRepository
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