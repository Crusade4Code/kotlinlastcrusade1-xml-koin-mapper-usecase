package com.example.kotlinlastcrusade1.data.repository

import com.example.kotlinlastcrusade1.core.coroutines.dispatchers.base.DispatchersProvider
import com.example.kotlinlastcrusade1.data.remote.GitHubApi
import com.example.kotlinlastcrusade1.domain.mapper.RepoMapper.toDomainList
import com.example.kotlinlastcrusade1.domain.model.Repo
import com.example.kotlinlastcrusade1.domain.repository.RepoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RepoRepositoryImpl(
    private val api: GitHubApi,
    private val dispatchersProvider: DispatchersProvider,
) : RepoRepository {
    private suspend fun getUserRepos(username: String): List<Repo> =
        withContext(dispatchersProvider.default) { api.getUserRepos(username).toDomainList() }

    // New method to return Flow with all repos
    override fun getUsersReposFlow(username: String): Flow<List<Repo>> = flow {
        // Emits the list of repos obtained from API
        val repoList = getUserRepos(username)
        emit(repoList)
    }
}