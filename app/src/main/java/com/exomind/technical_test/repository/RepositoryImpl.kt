package com.exomind.technical_test.repository

import com.exomind.technical_test.domain.Repository
import com.exomind.technical_test.domain.model.User
import com.exomind.technical_test.repository.local.room.RoomDataSource
import com.exomind.technical_test.repository.mapper.UserMapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class RepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val localDataSource: RoomDataSource,
    private val userMapper: UserMapper
) : Repository {

    private fun getUsersFromCache(): Single<List<User>> {
        return localDataSource.userDao().getAllUsers()
    }

    private fun getUsersFromRemote(): Single<List<User>> {
        return apiDataSource
            .getUsersList()
            .map { usersListApi ->
                usersListApi.map { userApi ->
                    userMapper.toDomain(userApi)
                }
            }
            .subscribeOn(Schedulers.computation())
            .doAfterSuccess { users ->
                Timber.d("Will put ${users.size} into local database")
                localDataSource.userDao().insertUsers(*users.toTypedArray())
            }
    }

    override fun getUsersList(): Single<List<User>> {
        Timber.d("getUsersList")

        val usersFromCache = getUsersFromCache()
            .subscribeOn(Schedulers.io())
            .blockingGet()

        return if (usersFromCache.isEmpty()) {
            getUsersFromRemote()
        } else {
            Single.just(usersFromCache)
        }
    }
}