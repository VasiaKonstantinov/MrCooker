package com.example.mrcooker.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DishDatabaseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "dishName") val dishName: String?,
    @ColumnInfo(name = "dishSteps") val dishSteps: String?,
    @ColumnInfo(name = "neededIngredients") val ingredients: String?,
    @ColumnInfo(name = "rate") val rate: Float?,
    @ColumnInfo(name = "timerInStep") val timerInStep: Int?,
    @ColumnInfo(name = "timerTime") val timerTime: Int?,
    @ColumnInfo(name = "isRedList") val isRedList: Boolean,
)
