package com.exomind.technical_test.domain

import com.exomind.technical_test.domain.model.Album
import com.exomind.technical_test.domain.model.User
import io.reactivex.Single

interface Repository {
    fun getUsersList(): Single<List<User>>
    fun searchUserByName(username: String): Single<List<User>>

    fun getAlbumsForUser(userId: Int): Single<List<Album>>
}