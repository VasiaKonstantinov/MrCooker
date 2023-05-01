package com.example.mrcooker.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DishDatabaseDao {

    @Query("SELECT * FROM DishDatabaseEntity")
    fun getAll(): List<DishDatabaseEntity>

    @Insert
    fun insertDish(dishDatabaseEntity: DishDatabaseEntity)

    @Update
    fun updateDish(dishDatabaseEntity: DishDatabaseEntity)

}