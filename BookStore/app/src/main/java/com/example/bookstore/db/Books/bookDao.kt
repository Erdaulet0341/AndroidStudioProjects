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
    fun getBookById(Sid:Int):Book

    @Query("delete from books where bookId like :Sid")
    fun deleteBookById(Sid: Int)

    @Query("update books set title=:title, description=:desc, cost=:cost where bookId=:Sid")
    fun updateBook(Sid:Int, title:String, desc:String, cost:Double)


    @Query("select * from books order by cost desc")
    fun getAllBiiksByCostDesc():Flow<List<Book>>

    @Query("select * from books order by cost asc")
    fun getAllBiiksByCostAsc():Flow<List<Book>>

}