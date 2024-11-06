package com.example.kotlinlastcrusade1.mapper.base

import com.example.kotlinlastcrusade1.domain.mapper.base.BaseMapper
import org.junit.Assert.assertEquals
import org.junit.Test

// Generic class for any implementation of BaseMapper
abstract class BaseMapperTest<DOMAIN, DTO>(
    private val mapper: BaseMapper<DOMAIN, DTO>,
    private val domainObject: DOMAIN,
    private val dtoObject: DTO
) {

    @Test
    fun `Domain to DTO mapping`() {
        val result = mapper.run { domainObject.toDto() }
        assertEquals(dtoObject, result)
    }

    @Test
    fun `DTO to Domain mapping`() {
        val result = mapper.run { dtoObject.toDomain() }
        assertEquals(domainObject, result)
    }

    @Test
    fun `List of Domain to List of DTO mapping`() {
        val domainList = listOf(domainObject)

        val result = mapper.run { domainList.toDtoList() }
        assertEquals(listOf(dtoObject), result)
    }

    @Test
    fun `List of DTO to List of Domain mapping`() {
        val dtoList = listOf(dtoObject)

        val result = mapper.run { dtoList.toDomainList() }
        assertEquals(listOf(domainObject), result)
    }
}