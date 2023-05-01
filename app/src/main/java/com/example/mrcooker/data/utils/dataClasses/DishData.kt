package com.example.mrcooker.data.utils.dataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DishData(
    val name: String,
    val rate: Float,
    var rateKey: String,
    val isRedList: Boolean,
    var isRedListKey: String
) : Parcelable
