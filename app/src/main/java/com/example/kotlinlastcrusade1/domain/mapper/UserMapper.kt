package com.example.kotlinlastcrusade1.domain.mapper

import com.example.kotlinlastcrusade1.data.model.UserDto
import com.example.kotlinlastcrusade1.domain.mapper.base.BaseMapper
import com.example.kotlinlastcrusade1.domain.model.User

object UserMapper: BaseMapper<User, UserDto> {
    override fun User.toDto() = UserDto (
        login = login,
        id = id,
        bio = bio,
        avatarUrl = avatarUrl
    )

    override fun UserDto.toDomain() = User (
        login = login,
        id = id,
        bio = bio,
        avatarUrl = avatarUrl
    )
}
