package com.example.myapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface userDAO {
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUsers():  Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)
//    @Query("DELETE FROM user_table")
//    suspend fun deleteAll()
}