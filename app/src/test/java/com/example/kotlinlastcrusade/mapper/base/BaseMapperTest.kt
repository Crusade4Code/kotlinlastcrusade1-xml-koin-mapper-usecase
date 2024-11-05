package com.example.kotlinlastcrusade.mapper.base

import com.example.kotlinlastcrusade.domain.mapper.base.BaseMapper
import org.junit.Assert.assertEquals
import org.junit.Test

// Classe base genérica de teste para qualquer implementação de BaseMapper
abstract class BaseMapperTest<DOMAIN, DTO>(
    private val mapper: BaseMapper<DOMAIN, DTO>,
    private val domainObject: DOMAIN,
    private val dtoObject: DTO
) {

    @Test
    fun `Domain to DTO mapping`() {
        // Executa o mapeamento e verifica se o resultado é o esperado
        val result = mapper.run { domainObject.toDto() }
        assertEquals(dtoObject, result)
    }

    @Test
    fun `DTO to Domain mapping`() {
        // Executa o mapeamento e verifica se o resultado é o esperado
        val result = mapper.run { dtoObject.toDomain() }
        assertEquals(domainObject, result)
    }

    @Test
    fun `List of Domain to List of DTO mapping`() {
        val domainList = listOf(domainObject)

        // Mapeia a lista e verifica o resultado
        val result = mapper.run { domainList.toDtoList() }
        assertEquals(listOf(dtoObject), result)
    }

    @Test
    fun `List of DTO to List of Domain mapping`() {
        val dtoList = listOf(dtoObject)

        // Mapeia a lista e verifica o resultado
        val result = mapper.run { dtoList.toDomainList() }
        assertEquals(listOf(domainObject), result)
    }
}