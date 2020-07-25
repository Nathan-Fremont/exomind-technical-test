package com.exomind.technical_test.repository.api

import com.exomind.technical_test.repository.ApiDataSource
import com.exomind.technical_test.repository.api.retrofit.ApiService

class ApiDataSourceImpl(
    private val apiService: ApiService
) : ApiDataSource {
}