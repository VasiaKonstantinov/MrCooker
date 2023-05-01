package com.example.mrcooker.presentation.fragments.chooseDishFragment

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mrcooker.data.room.CookerDatabase
import com.example.mrcooker.data.utils.dataClasses.*
import com.example.mrcooker.data.utils.parseStringFromDatabase
import com.example.mrcooker.data.utils.recepts.RecipeForCooking
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChooseDishFragmentViewModel @Inject constructor(private val application: Application) :
    ViewModel() {

    //  val dishList = mutableListOf<RecipeStepAndIngredientsList>()
    private val database: CookerDatabase = CookerDatabase.getDatabase(application)

    private fun getDishList(sharedPref: SharedPreferences?): MutableList<RecipeStepAndIngredientsList> {
        val potatoRecipe = RecipeForCooking(
            application,
            sharedPref!!
        ).createFriedPotatoesRecipe()

        val pastaRecipe = RecipeForCooking(
            application,
            sharedPref
        ).createPastaRecipe()

        val meetPee = RecipeForCooking(
            application,
            sharedPref
        ).createMeatPie()

        val soup = RecipeForCooking(
            application,
            sharedPref
        ).createQuickAndEasyChickenNoodleSoup()

        val result = mutableListOf<RecipeStepAndIngredientsList>()
        result.add(potatoRecipe)
        result.add(pastaRecipe)
        result.add(meetPee)
        result.add(soup)
        val dishFromDatabase = database.dishDatabaseDao().getAll()

        for (dish in dishFromDatabase) {
            val parsedIngendients = dish.ingredients.toString().parseStringFromDatabase()
            val parsedSteps = dish.dishSteps.toString().parseStringFromDatabase()
            val ingredientsList = mutableListOf<Ingredients>()
            val cocingStepList = mutableListOf<CookingStep>()
            val dishData = DishData(
                dish.dishName.toString(),
                dish.rate ?: 0f,
                dish.dishName.toString() + dish.rate,
                dish.isRedList,
                dish.dishName.toString() + dish.isRedList
            )
            for (i in parsedIngendients) {
                ingredientsList.add(Ingredients(i))
            }
            for (i in parsedSteps) {
                cocingStepList.add(CookingStep(i, 0, false, dish.dishName + dish.rate))
            }
            result.add(RecipeStepAndIngredientsList(ingredientsList, cocingStepList, dishData))
        }


        Log.d("dffdfdg", potatoRecipe.ingredientsList.toString())
        Log.d("dffdfdg", result[1].ingredientsList.toString())
        return result
    }

    fun getDishListByProducts(
        productList: Array<ProductData>,
        sharedPref: SharedPreferences?
    ): MutableList<RecipeStepAndIngredientsList> {
        val allDishList = getDishList(sharedPref)
        val result = mutableListOf<RecipeStepAndIngredientsList>()

        val inputStringIngredients = mutableListOf<String>()
        val dishStringList = mutableListOf<String>()
        for (i in productList) {
            inputStringIngredients.add(i.productName)
        }

        for (dish in allDishList) {
            for (i in dish.ingredientsList) {
                dishStringList.add(i.ingredient)
            }
            var dishInCurrent = true
            for (dishIng in dishStringList) {
                var ingInCurrent = false
                for (inputIng in inputStringIngredients) {
                    if (dishIng == inputIng) ingInCurrent = true
                }
                if (!ingInCurrent) dishInCurrent = false
            }

            dishStringList.clear()
            if (dishInCurrent) result.add(dish)
        }
        return result
    }
}