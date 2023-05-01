package com.example.mrcooker.presentation.fragments.loginFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.mrcooker.data.room.CookerDatabase
import com.example.mrcooker.data.room.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(application: Application) : ViewModel() {

    val database = CookerDatabase.getDatabase(application)

    fun tryToLogin(userData: UserData): Boolean {
        val userDataList = getUserDataList()
        var result = false
        for (user in userDataList) {
            if (user.userName == userData.userName && user.password == userData.password) result = true
        }
        return result
    }

    private fun getUserDataList() = database.userDataDatabaseDao().getAll()
}