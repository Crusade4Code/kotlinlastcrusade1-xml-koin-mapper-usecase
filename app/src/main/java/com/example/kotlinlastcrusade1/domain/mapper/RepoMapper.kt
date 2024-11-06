package com.example.kotlinlastcrusade1.domain.mapper

import com.example.kotlinlastcrusade1.data.model.RepoDto
import com.example.kotlinlastcrusade1.domain.mapper.base.BaseMapper
import com.example.kotlinlastcrusade1.domain.model.Repo

object RepoMapper: BaseMapper<Repo, RepoDto> {
    override fun Repo.toDto() = RepoDto (
        id = id,
        name = name,
        url = url,
        description = description
    )

    override fun RepoDto.toDomain() = Repo (
        id = id,
        name = name,
        url = url,
        description = description
    )
}
