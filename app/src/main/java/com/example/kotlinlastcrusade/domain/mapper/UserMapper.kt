package com.example.kotlinlastcrusade.domain.mapper

import com.example.kotlinlastcrusade.data.model.UserDto
import com.example.kotlinlastcrusade.domain.mapper.base.BaseMapper
import com.example.kotlinlastcrusade.domain.model.User

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
