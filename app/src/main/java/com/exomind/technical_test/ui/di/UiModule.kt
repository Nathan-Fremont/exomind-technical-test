package com.exomind.technical_test.ui.di

import com.exomind.technical_test.repository.mapper.UserMapper
import com.exomind.technical_test.ui.users_list.UsersListViewModel
import com.exomind.technical_test.ui.users_list.mapper.UserUiMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiUsersListViewModelModule = module {
    viewModel {
        UsersListViewModel(
            usersListUseCase = get(),
            userUiMapper = get()
        )
    }
}

val uiMapperModule = module {
    single {
        UserUiMapper()
    }
}

val koinUiModules = listOf(
    uiUsersListViewModelModule,
    uiMapperModule
)