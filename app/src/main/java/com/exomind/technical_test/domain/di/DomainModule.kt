package com.exomind.technical_test.domain.di

import com.exomind.technical_test.domain.usecase.GetUsersListUseCase
import com.exomind.technical_test.domain.usecase.SearchUserByNameUseCase
import org.koin.dsl.module

val domainUseCaseModule = module {
    factory {
        GetUsersListUseCase(
            repository = get()
        )
    }
    factory {
        SearchUserByNameUseCase(
            repository = get()
        )
    }
}

val koinDomainModules = listOf(
    domainUseCaseModule
)