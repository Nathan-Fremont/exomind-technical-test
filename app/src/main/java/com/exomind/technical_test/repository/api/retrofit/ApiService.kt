package com.exomind.technical_test.repository.api.retrofit

import com.exomind.technical_test.repository.api.model.UserApi
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsersList(): Single<List<UserApi>>
}