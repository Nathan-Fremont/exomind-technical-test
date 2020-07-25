package com.exomind.technical_test.repository.api

import com.exomind.technical_test.repository.ApiDataSource
import com.exomind.technical_test.repository.api.model.UserApi
import com.exomind.technical_test.repository.api.retrofit.ApiService
import io.reactivex.Single

class ApiDataSourceImpl(
    private val apiService: ApiService
) : ApiDataSource {

    override fun getUsersList(): Single<List<UserApi>> {
        return apiService.getUsersList();
    }
}