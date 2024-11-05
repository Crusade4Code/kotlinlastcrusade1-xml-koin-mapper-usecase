package com.example.kotlinlastcrusade.domain.model

data class User(
    val login: String,
    val id: Int,
    val bio: String?,
    val avatarUrl: String
)