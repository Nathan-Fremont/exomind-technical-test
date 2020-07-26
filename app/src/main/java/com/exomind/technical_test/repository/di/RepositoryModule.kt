package com.exomind.technical_test.repository.di

import com.exomind.technical_test.domain.Repository
import com.exomind.technical_test.repository.ApiDataSource
import com.exomind.technical_test.repository.RepositoryImpl
import com.exomind.technical_test.repository.api.ApiDataSourceImpl
import com.exomind.technical_test.repository.api.retrofit.ApiRetrofitFactory
import com.exomind.technical_test.repository.local.room.RoomDataSource
import com.exomind.technical_test.repository.mapper.AlbumMapper
import com.exomind.technical_test.repository.mapper.PhotoMapper
import com.exomind.technical_test.repository.mapper.UserMapper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val apiRetrofitModule = module {
    single {
        ApiRetrofitFactory().buildApiService(
            ApiRetrofitFactory().buildApiRetrofit()
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
            userMapper = get(),
            albumMapper = get(),
            photoMapper = get()
        ) as Repository
    }
}

val repositoryMapperModule = module {
    single {
        UserMapper()
    }
    single {
        AlbumMapper()
    }
    single {
        PhotoMapper()
    }
}

val koinRepositoryModules = listOf(
    apiRetrofitModule,
    apiDataSourceModule,
    localDataModule,
    repositoryModule,
    repositoryMapperModule
)