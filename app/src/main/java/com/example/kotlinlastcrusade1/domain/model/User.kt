package com.example.kotlinlastcrusade1.domain.model

data class User(
    val login: String,
    val id: Int,
    val bio: String?,
    val avatarUrl: String
)