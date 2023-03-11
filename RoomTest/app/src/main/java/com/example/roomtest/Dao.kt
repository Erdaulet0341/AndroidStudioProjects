package com.example.roomtest
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertItem(item:Item)

    @Query("SELECT * FROM Items")
    fun getAllItem(): Flow<List<Item>>

    @Query("select * from Items where id%2=0")
    fun getAllEvenItems(): Flow<List<Item>>
}