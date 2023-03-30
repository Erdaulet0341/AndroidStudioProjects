package com.example.newsus.savedItems

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [sNews::class], version = 1)
abstract class sDb: RoomDatabase() {

    abstract fun getNewsDao():sDao

    companion object{
        fun getNewsDb(context: Context):sDb{
            return Room.databaseBuilder(
                context.applicationContext,
                sDb::class.java,
                "sNews.db"
            ).build()
        }
    }

}