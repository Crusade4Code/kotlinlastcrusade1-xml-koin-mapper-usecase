package com.example.kotlinlastcrusade1.domain.repository

import com.example.kotlinlastcrusade1.domain.model.Repo

interface RepoRepository {
    suspend fun getUserRepos(username: String): List<Repo>
}