package com.example.mrcooker.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "dateOfManufacture") val dateOfManufacture: Int
)