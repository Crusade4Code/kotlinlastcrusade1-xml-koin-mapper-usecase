package com.example.kotlinlastcrusade.data.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    val login: String,
    val id: Int,
    val bio: String?,
    @SerializedName("avatar_url")  val avatarUrl: String
)