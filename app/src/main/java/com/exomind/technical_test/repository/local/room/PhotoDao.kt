package com.exomind.technical_test.repository.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exomind.technical_test.domain.model.Photo
import io.reactivex.Single

@Dao
interface PhotoDao {
    @Query("SELECT * FROM Photo WHERE album_id = :albumId")
    fun getAllPhotosForAlbum(albumId: Int): Single<List<Photo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPhotos(vararg photos: Photo)
}