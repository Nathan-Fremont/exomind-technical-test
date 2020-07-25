package com.exomind.technical_test.repository

import com.exomind.technical_test.domain.Repository
import com.exomind.technical_test.domain.model.User
import com.exomind.technical_test.repository.mapper.UserMapper
import io.reactivex.Single

class RepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val userMapper: UserMapper
) : Repository {

    override fun getUsersList(): Single<List<User>> {
        return apiDataSource
            .getUsersList()
            .map { usersListApi ->
                usersListApi.map {  userApi ->
                    userMapper.toDomain(userApi)
                }
            }
    }
}