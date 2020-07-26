package com.exomind.technical_test.domain.di

import com.exomind.technical_test.domain.usecase.GetUsersListUseCase
import org.koin.dsl.module

val domainUseCaseModule = module {
    factory {
        GetUsersListUseCase(
            repository = get()
        )
    }
}

val koinDomainModules = listOf(
    domainUseCaseModule
)