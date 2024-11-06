package com.example.kotlinlastcrusade1.domain.mapper.base

interface BaseMapper<DOMAIN, DTO> {
    fun DOMAIN.toDto(): DTO
    fun DTO.toDomain(): DOMAIN
    fun List<DOMAIN>.toDtoList() = map { it.toDto() }
    fun List<DTO>.toDomainList() = map { it.toDomain() }
}