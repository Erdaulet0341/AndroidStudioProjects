package com.example.bookstore.db.Books

import android.accounts.AuthenticatorDescription
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    var bookId: Int? = null,
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "author")
    var author:String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "cost")
    var cost: Double,
)