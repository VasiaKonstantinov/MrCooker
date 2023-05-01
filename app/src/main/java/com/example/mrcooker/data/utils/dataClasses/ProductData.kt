package com.example.mrcooker.data.utils.dataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductData(val productName: String, val id: Int, var selected: Boolean = false) :
    Parcelable