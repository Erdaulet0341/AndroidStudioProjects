package com.example.bookstore.db.Books

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1)
abstract class bookDb: RoomDatabase() {

    abstract fun getBookDao():bookDao

    companion object{
        fun getBookDb(context: Context):bookDb{
            return Room.databaseBuilder(
                context.applicationContext,
                bookDb::class.java,
                "books.db"
            ).build()
        }
    }

}