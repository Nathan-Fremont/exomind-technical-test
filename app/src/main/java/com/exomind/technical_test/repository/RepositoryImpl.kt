package com.exomind.technical_test.repository

import com.exomind.technical_test.domain.Repository
import com.exomind.technical_test.domain.model.Album
import com.exomind.technical_test.domain.model.Photo
import com.exomind.technical_test.domain.model.User
import com.exomind.technical_test.repository.local.room.RoomDataSource
import com.exomind.technical_test.repository.mapper.AlbumMapper
import com.exomind.technical_test.repository.mapper.PhotoMapper
import com.exomind.technical_test.repository.mapper.UserMapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class RepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val localDataSource: RoomDataSource,
    private val userMapper: UserMapper,
    private val albumMapper: AlbumMapper,
    private val photoMapper: PhotoMapper
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
                Timber.d("Will put ${users.size} users into local database")
                localDataSource.userDao().insertUsers(*users.toTypedArray())
            }
    }

    override fun getUsersList(): Single<List<User>> {
        Timber.d("getUsersList")

        val usersFromCache = getUsersFromCache()
            .subscribeOn(Schedulers.io())
            .blockingGet()

        return if (usersFromCache.isEmpty()) {
            Timber.d("No users in cache, will get them from remote")
            getUsersFromRemote()
        } else {
            Timber.d("Got users in cache")
            Single.just(usersFromCache)
        }
    }

    override fun searchUserByName(username: String): Single<List<User>> {
        return apiDataSource.searchUserByName(username)
            .map { usersListApi ->
                usersListApi.map { userApi ->
                    userMapper.toDomain(userApi)
                }
            }
    }

    private fun getAlbumsFromCacheForUserId(userId: Int): Single<List<Album>> {
        return localDataSource
            .albumDao()
            .getAlbumsForUser(userId)
    }

    private fun getAlbumsFromRemoteForUserId(userId: Int): Single<List<Album>> {
        return apiDataSource
            .getAlbumsForUser(userId)
            .map { albumsListApi ->
                albumsListApi.map { albumApi ->
                    albumMapper.toDomain(albumApi)
                }
            }
            .subscribeOn(Schedulers.computation())
            .doAfterSuccess { albums ->
                Timber.d("Will put ${albums.size} albums into local database")
                localDataSource.albumDao().insertAlbums(*albums.toTypedArray())
            }
    }

    override fun getAlbumsForUser(userId: Int): Single<List<Album>> {
        Timber.d("getAlbumsForUser $userId")

        val albumsFromCache = getAlbumsFromCacheForUserId(userId)
            .subscribeOn(Schedulers.io())
            .blockingGet()

        return if (albumsFromCache.isEmpty()) {
            Timber.d("No albums in cache, will get them from remote")
            getAlbumsFromRemoteForUserId(userId)
        } else {
            Timber.d("Got albums in cache")
            Single.just(albumsFromCache)
        }
    }

    override fun getPhotosForAlbum(userId: Int, albumId: Int): Single<List<Photo>> {
        return apiDataSource.getPhotosForUser(userId)
            .map { photosListApi ->
                photosListApi.map { photoApi ->
                    photoMapper.toDomain(photoApi)
                }
            }
            .flatMap { photosList ->
                Single.just(
                    photosList.filter { photo ->
                        photo.albumId == albumId
                    }
                )
            }
    }
}