package com.example.mrcooker.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [RedProduct::class, DishDatabaseEntity::class, UserData::class, BookEntity::class], version = 5)
abstract class CookerDatabase : RoomDatabase() {
    abstract fun redProductDao(): RedProductDao
    abstract fun dishDatabaseDao(): DishDatabaseDao
    abstract fun userDataDatabaseDao(): UserDataDao
    abstract fun libraryDatabaseDao(): LibraryDatabaseDao

    companion object {
        private var INSTANCE: CookerDatabase? = null
        fun getDatabase(context: Context): CookerDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, CookerDatabase::class.java, "cooker_database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}