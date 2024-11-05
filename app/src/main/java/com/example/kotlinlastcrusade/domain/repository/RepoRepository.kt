package com.example.kotlinlastcrusade.domain.repository

import com.example.kotlinlastcrusade.domain.model.Repo

interface RepoRepository {
    suspend fun getUserRepos(username: String): List<Repo>
}