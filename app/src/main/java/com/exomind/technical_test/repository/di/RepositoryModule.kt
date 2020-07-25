package com.exomind.technical_test.repository.di

import com.exomind.technical_test.domain.Repository
import com.exomind.technical_test.repository.ApiDataSource
import com.exomind.technical_test.repository.RepositoryImpl
import com.exomind.technical_test.repository.api.ApiDataSourceImpl
import com.exomind.technical_test.repository.api.retrofit.ApiRetrofitFactory
import com.exomind.technical_test.repository.local.room.RoomDataSource
import com.exomind.technical_test.repository.mapper.UserMapper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val apiRetrofitModule = module {
    single {
        ApiRetrofitFactory(androidContext()).buildApiService(
            ApiRetrofitFactory(androidContext()).buildApiRetrofit()
        )
    }
}

val apiDataSourceModule = module {
    single {
        ApiDataSourceImpl(
            apiService = get()
        ) as ApiDataSource
    }
}

val localDataModule = module {
    single {
        RoomDataSource.buildDatabase(androidContext()) as RoomDataSource
    }
}

val repositoryModule = module {
    single {
        RepositoryImpl(
            apiDataSource = get(),
            localDataSource = get(),
            userMapper = get()
        ) as Repository
    }
}

val repositoryMapperModule = module {
    single {
        UserMapper()
    }
}

val koinRepositoryModules = listOf(
    apiRetrofitModule,
    apiDataSourceModule,
    localDataModule,
    repositoryModule,
    repositoryMapperModule
)