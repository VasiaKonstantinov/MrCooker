package com.example.mrcooker.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LibraryDatabaseDao {

    @Query("SELECT * FROM BookEntity")
    fun getAll(): List<BookEntity>

    @Insert
    fun insertBook(bookEntity: BookEntity)

    @Delete
    fun removeBook(bookEntity: BookEntity)

}