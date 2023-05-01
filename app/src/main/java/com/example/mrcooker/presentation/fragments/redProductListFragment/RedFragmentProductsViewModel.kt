package com.example.mrcooker.presentation.fragments.redProductListFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.mrcooker.data.room.CookerDatabase
import com.example.mrcooker.data.room.RedProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RedFragmentProductsViewModel @Inject constructor (application: Application): ViewModel() {

    private val database: CookerDatabase = CookerDatabase.getDatabase(application)

    fun getRedListProducts(): List<RedProduct> {
        return database.redProductDao().getAll()
    }

    fun deleteRedProduct(redProduct: RedProduct) {
        database.redProductDao().deleteByUserId(redProduct.id)
    }
}