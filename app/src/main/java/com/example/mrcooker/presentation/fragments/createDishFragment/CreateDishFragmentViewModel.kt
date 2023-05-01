package com.example.mrcooker.presentation.fragments.createDishFragment

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mrcooker.data.room.CookerDatabase
import com.example.mrcooker.data.room.DishDatabaseEntity
import com.example.mrcooker.data.room.RedProduct
import com.example.mrcooker.data.utils.dataClasses.ProductData
import com.example.mrcooker.data.utils.dataClasses.Steps
import com.example.mrcooker.data.utils.enumClasses.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateDishFragmentViewModel @Inject constructor(application: Application): ViewModel() {
    private val database: CookerDatabase = CookerDatabase.getDatabase(application)
    private val selectedProducts = mutableListOf<ProductData>()


    fun getSelectedProductsList(): List<ProductData> = selectedProducts.toList()
    fun addItemInSelectedProductsList(productData: ProductData) = selectedProducts.add(productData)
    fun clearSelectedProductsList() = selectedProducts.clear()

    fun deleteItemInSelectedProductList(removedProductData: ProductData) {
        var mustBeRemovedElement: ProductData? = null
        for (product in selectedProducts) {
            if (product == removedProductData) mustBeRemovedElement = product
        }
        selectedProducts.remove(mustBeRemovedElement)
    }

    fun createProductDataList(): List<ProductData> {
        val resultProductsDataList: MutableList<ProductData> = mutableListOf()
        val products = Products.values()
        for (i in products) {
            resultProductsDataList.add(ProductData(i.name, i.ordinal, false))
        }
        return resultProductsDataList.sortedBy { it.productName }.toList()
    }

    fun insertRedProduct(productData: ProductData) {
        database.redProductDao().insertRedProduct(RedProduct(productName = productData.productName))
    }

    private fun getRedListProducts(): List<RedProduct> {
        return database.redProductDao().getAll()
    }

    fun createProductListWithoutRedProducts(allProductList: List<ProductData>): List<ProductData> {
        val redProductsList = getRedListProducts()
        allProductList.filter { allProductData ->
            var result = true
            for (redProduct in redProductsList) {
                if (allProductData.productName == redProduct.productName) result = false
            }
            result
        }
        return allProductList
    }

    val steps = mutableListOf<Steps>()
    var timerInStep: Int = 0
    var timerTime: Int = 0

    fun addStep(step: Steps) {
        steps.add(step)
    }

    fun createDish(dishName: String) {
        var dishSteps: String = ""
        var ingredients: String = ""
        for (i in steps) {
            dishSteps += i.stepDiscription + barer
        }
        for (i in selectedProducts) {
            ingredients += i.productName + barer
        }
        Log.d("fgdfdgfd", selectedProducts.toString())
        val dish = DishDatabaseEntity(
            dishName = dishName,
            dishSteps = dishSteps,
            ingredients = ingredients,
            rate = 0f,
            timerInStep = timerInStep,
            timerTime = timerTime,
            isRedList = false
        )
        timerTime = 0
        timerTime = 0
        database.dishDatabaseDao().insertDish(dish)
    }

    companion object {
        val barer = "BARER_KEY"
    }
}