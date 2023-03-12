package com.example.bookstore.db.bookUsers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1)
abstract class userDb: RoomDatabase() {

    abstract fun getDao(): userDao

    companion object{
        fun getUserDb(context: Context): userDb{
            return Room.databaseBuilder(
                context.applicationContext,
                userDb::class.java,
                "users.db"
            ).build()
        }
    }

}