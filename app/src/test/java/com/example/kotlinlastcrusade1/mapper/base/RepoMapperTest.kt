package com.example.kotlinlastcrusade1.mapper.base

import com.example.kotlinlastcrusade1.data.model.RepoDto
import com.example.kotlinlastcrusade1.domain.mapper.RepoMapper
import com.example.kotlinlastcrusade1.domain.model.Repo

class RepoMapperTest : BaseMapperTest<Repo, RepoDto>(
    mapper = RepoMapper,
    domainObject = Repo(id = 1, name = "Repo1", url = "Repo1 url", description = "Repo1 description"),
    dtoObject = RepoDto(id = 1, name = "Repo1", url = "Repo1 url", description = "Repo1 description"),
)