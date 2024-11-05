package com.example.kotlinlastcrusade.domain.model

data class Repo(
    val id: Int,
    val name: String,
    val url: String,
    val description: String?
)