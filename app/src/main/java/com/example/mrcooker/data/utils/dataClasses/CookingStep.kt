package com.example.mrcooker.data.utils.dataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CookingStep(
    val cookingStepInfo: String,
    var cookingStepTime: Int,
    var isLastStep: Boolean = false,
    var rateKey: String
) : Parcelable