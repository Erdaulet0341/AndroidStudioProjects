package com.example.newsus.savedItems

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsus.Source

@Entity(tableName = "sNews")
data class sNews(
    @PrimaryKey(autoGenerate = true)
    var sId: Int? = null,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String,
//    @ColumnInfo(name = "isSave")
//    val ifSave: Boolean
    )