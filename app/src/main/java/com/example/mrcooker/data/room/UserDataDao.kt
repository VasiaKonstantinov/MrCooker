package com.example.mrcooker.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDataDao {

    @Query("SELECT * FROM UserData")
    fun getAll(): List<UserData>

    @Insert
    fun insertUserData(userData: UserData)
}