package com.example.bookstore.db.Books

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.selects.select


@Dao
interface bookDao {

    @Insert
    fun insertBook(book:Book)

    @Query("select * from books")
    fun getAllBooks(): Flow<List<Book>>

    @Query("select * from books where bookId like :Sid")
    fun getUserById(Sid:Int):Book
}