package com.exomind.technical_test.ui.di

import com.exomind.technical_test.ui.albums_list.AlbumsListViewModel
import com.exomind.technical_test.ui.albums_list.mapper.AlbumUiMapper
import com.exomind.technical_test.ui.users_list.UsersListViewModel
import com.exomind.technical_test.ui.users_list.mapper.UserUiMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiUsersListViewModelModule = module {
    viewModel {
        UsersListViewModel(
            usersListUseCase = get(),
            searchUserByNameUseCase = get(),
            userUiMapper = get()
        )
    }
}

val uiAlbumsListViewModelModule = module {
    viewModel {
        AlbumsListViewModel(
            albumsListUseCase = get(),
            albumUiMapper = get()
        )
    }
}

val uiMapperModule = module {
    single {
        UserUiMapper()
    }
    single {
        AlbumUiMapper()
    }
}

val koinUiModules = listOf(
    uiUsersListViewModelModule,
    uiAlbumsListViewModelModule,
    uiMapperModule
)