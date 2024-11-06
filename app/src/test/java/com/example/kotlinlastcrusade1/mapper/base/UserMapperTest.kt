package com.example.kotlinlastcrusade1.mapper.base

import com.example.kotlinlastcrusade1.data.model.UserDto
import com.example.kotlinlastcrusade1.domain.mapper.UserMapper
import com.example.kotlinlastcrusade1.domain.model.User

class UserMapperTest : BaseMapperTest<User, UserDto>(
    mapper = UserMapper,
    domainObject = User(id = 1, login = "User1", bio = "User1 Bio", avatarUrl = "User1 url"),
    dtoObject = UserDto(id = 1, login = "User1", bio = "User1 Bio", avatarUrl = "User1 url"),
)