package com.example.mrcooker.presentation.fragments.regestrationFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.room.Insert
import com.example.mrcooker.data.room.CookerDatabase
import com.example.mrcooker.data.room.UserData
import com.example.mrcooker.data.room.UserDataDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(application: Application) : ViewModel() {

    private val database = CookerDatabase.getDatabase(application)

    fun insertUserData(password: String, userName: String) {
        database.userDataDatabaseDao()
            .insertUserData(UserData(userName = userName, password = password))
    }
}