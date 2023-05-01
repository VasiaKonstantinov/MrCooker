package com.example.mrcooker.data.utils

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun <T> Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope) =
    lifecycleScope.launchWhenStarted {
        this@launchWhenStarted.collect()
    }

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun String.parseStringFromDatabase(): List<String> {
    val barer = "BARER_KEY"
    val parsedStingList = mutableListOf<String>()
    var parsedString: String = this
    var cycleIsNeed = true
    while (cycleIsNeed) {
        val parsedElement = parsedString.substringBefore(barer)
        parsedString = parsedString.replace("$parsedElement$barer", "")
        parsedStingList.add(parsedElement.trim())
        if (parsedString.isEmpty()) cycleIsNeed = false
        Log.d("ewrwerewfgdfg", this.toString())
    }
    return parsedStingList
}
