package com.exomind.technical_test.repository

import com.exomind.technical_test.repository.api.model.UserApi
import io.reactivex.Single

interface ApiDataSource {
    fun getUsersList(): Single<List<UserApi>>
}