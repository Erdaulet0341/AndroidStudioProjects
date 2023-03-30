package com.example.newsus.savedItems

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface sDao {

    @Insert
    fun insertNews(sNews: sNews)

    @Query("select * from sNews")
    fun getAllSavedNews(): Flow<List<sNews>>


    @Query("delete from sNews where urlToImage like :img")
    fun deleteNewsByUrl(img: String)

}