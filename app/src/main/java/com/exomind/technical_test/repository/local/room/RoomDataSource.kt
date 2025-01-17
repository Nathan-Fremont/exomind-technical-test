package com.exomind.technical_test.repository.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.exomind.technical_test.domain.model.Album
import com.exomind.technical_test.domain.model.Photo
import com.exomind.technical_test.domain.model.User
import com.exomind.technical_test.repository.local.AlbumDao
import com.exomind.technical_test.repository.local.UserDao

@Database(entities = [User::class, Album::class, Photo::class], version = 3)
abstract class RoomDataSource : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun albumDao(): AlbumDao
    abstract fun photoDao(): PhotoDao

    companion object {
        private const val DATABASE_NAME = "EXOMIND_ROOM_DATABASE"

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE `Album` (`id` INTEGER NOT NULL , `user_id` INTEGER NOT NULL, `title` TEXT NOT NULL, PRIMARY KEY(`id`))"
                )
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE `Photo` (`id` INTEGER NOT NULL , `album_id` INTEGER NOT NULL, `title` TEXT NOT NULL, `url` TEXT NOT NULL, `thumbnail_url` TEXT NOT NULL, PRIMARY KEY(`id`))"

                )
            }
        }

        fun buildDatabase(context: Context): RoomDataSource {
            return Room.databaseBuilder(
                context,
                RoomDataSource::class.java,
                DATABASE_NAME
            )
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}