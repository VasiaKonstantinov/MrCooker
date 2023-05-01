package com.example.mrcooker.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RedProductDao {

    @Query("SELECT * FROM RedProduct")
    fun getAll(): List<RedProduct>

    @Insert
    fun insertRedProduct(redProduct: RedProduct)

    @Query("DELETE FROM RedProduct WHERE id = :redProductId")
    fun deleteByUserId(redProductId: Int)
}