package com.example.kotlinlastcrusade.domain.mapper

import com.example.kotlinlastcrusade.data.model.RepoDto
import com.example.kotlinlastcrusade.domain.mapper.base.BaseMapper
import com.example.kotlinlastcrusade.domain.model.Repo

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
