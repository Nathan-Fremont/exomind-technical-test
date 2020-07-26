package com.exomind.technical_test.repository.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exomind.technical_test.domain.model.User
import com.exomind.technical_test.repository.local.UserDao

@Database(entities = [User::class], version = 1)
abstract class RoomDataSource : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "EXOMIND_ROOM_DATABASE"

        fun buildDatabase(context: Context): RoomDataSource {
            return Room.databaseBuilder(
                context,
                RoomDataSource::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}