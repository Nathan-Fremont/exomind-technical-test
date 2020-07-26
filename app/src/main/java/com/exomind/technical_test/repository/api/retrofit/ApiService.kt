package com.exomind.technical_test.repository.api.retrofit

import com.exomind.technical_test.repository.api.model.AlbumApi
import com.exomind.technical_test.repository.api.model.PhotoApi
import com.exomind.technical_test.repository.api.model.UserApi
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsersList(): Single<List<UserApi>>

    @GET("users")
    fun searchUserByName(@Query("username") username: String): Single<List<UserApi>>

    @GET("users/{user_id}/albums")
    fun getAlbumsForUser(@Path("user_id") userId: Int) : Single<List<AlbumApi>>

    @GET("users/{user_id}/photos")
    fun getPhotosForUser(@Path("user_id") userId: Int) : Single<List<PhotoApi>>
}