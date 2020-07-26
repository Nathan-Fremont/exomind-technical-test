package com.exomind.technical_test.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exomind.technical_test.domain.model.Album
import io.reactivex.Single

@Dao
interface AlbumDao {
    @Query("SELECT * FROM Album WHERE user_id = :userId")
    fun getAlbumsForUser(userId: Int): Single<List<Album>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAlbums(vararg albums: Album)
}