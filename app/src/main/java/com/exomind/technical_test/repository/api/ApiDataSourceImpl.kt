package com.exomind.technical_test.repository.api

import com.exomind.technical_test.repository.ApiDataSource
import com.exomind.technical_test.repository.api.model.AlbumApi
import com.exomind.technical_test.repository.api.model.PhotoApi
import com.exomind.technical_test.repository.api.model.UserApi
import com.exomind.technical_test.repository.api.retrofit.ApiService
import io.reactivex.Single

class ApiDataSourceImpl(
    private val apiService: ApiService
) : ApiDataSource {

    override fun getUsersList(): Single<List<UserApi>> {
        return apiService.getUsersList()
    }

    override fun searchUserByName(username: String): Single<List<UserApi>> {
        return apiService.searchUserByName(username)
    }

    override fun getAlbumsForUser(userId: Int): Single<List<AlbumApi>> {
        return apiService.getAlbumsForUser(userId)
    }

    override fun getPhotosForUser(userId: Int): Single<List<PhotoApi>> {
        return apiService.getPhotosForUser(userId)
    }
}