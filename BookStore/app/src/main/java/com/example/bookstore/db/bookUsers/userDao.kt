package com.example.bookstore.db.bookUsers

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface userDao {

    @Insert
    fun insertUser(user: User)

    @Query("select * from users")
    fun getAllUser(): Flow<List<User>>

    @Query("update users set name=:Sname, surname=:Ssurname, email=:Semail, password=:sPass, confPass=:SconfPass where id like :Sid")
    fun updateUser(Sid:Int, Sname:String, Ssurname:String, Semail:String, sPass:String, SconfPass:String)

}