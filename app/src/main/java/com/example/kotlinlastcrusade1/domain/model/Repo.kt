package com.example.kotlinlastcrusade1.domain.model

data class Repo(
    val id: Int,
    val name: String,
    val url: String,
    val description: String?
)