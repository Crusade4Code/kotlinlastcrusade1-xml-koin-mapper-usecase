package com.example.kotlinlastcrusade.data.remote

import com.example.kotlinlastcrusade.data.model.RepoDto
import com.example.kotlinlastcrusade.data.model.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users")
    suspend fun getUsers(): List<UserDto>

    @GET("users/{username}")
    suspend fun getUserDetails(@Path("username") username: String): UserDto

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): List<RepoDto>
}