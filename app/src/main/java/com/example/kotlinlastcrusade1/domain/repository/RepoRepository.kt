package com.example.kotlinlastcrusade1.domain.repository

import com.example.kotlinlastcrusade1.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    fun getUsersReposFlow(username: String): Flow<List<Repo>>
}