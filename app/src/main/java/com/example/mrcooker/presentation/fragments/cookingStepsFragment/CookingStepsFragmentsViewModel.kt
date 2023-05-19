package com.example.mrcooker.presentation.fragments.cookingStepsFragment

import androidx.lifecycle.ViewModel

class CookingStepsFragmentsViewModel : ViewModel() {

    fun mapTime(time: String): String {
        val intTime = time.toInt()
        val hours = intTime / 3600
        val minutes = intTime / 60
        val seconds = (intTime - minutes * 60)
        return "$hours : $minutes : $seconds"
    }
}