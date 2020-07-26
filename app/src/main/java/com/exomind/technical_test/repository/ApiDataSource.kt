package com.exomind.technical_test.repository

import com.exomind.technical_test.repository.api.model.AlbumApi
import com.exomind.technical_test.repository.api.model.PhotoApi
import com.exomind.technical_test.repository.api.model.UserApi
import io.reactivex.Single

interface ApiDataSource {
    fun getUsersList(): Single<List<UserApi>>
    fun searchUserByName(username: String): Single<List<UserApi>>

    fun getAlbumsForUser(userId: Int): Single<List<AlbumApi>>

    fun getPhotosForUser(userId: Int): Single<List<PhotoApi>>
}