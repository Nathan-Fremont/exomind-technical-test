package com.exomind.technical_test.repository.mapper

abstract class BaseDomainMapper<API, DOMAIN> {
    abstract fun toDomain(api: API): DOMAIN
}