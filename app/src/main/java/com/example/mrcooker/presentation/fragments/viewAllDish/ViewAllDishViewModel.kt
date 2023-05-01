package com.example.mrcooker.presentation.fragments.viewAllDish

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mrcooker.data.room.CookerDatabase
import com.example.mrcooker.data.utils.dataClasses.CookingStep
import com.example.mrcooker.data.utils.dataClasses.DishData
import com.example.mrcooker.data.utils.dataClasses.Ingredients
import com.example.mrcooker.data.utils.dataClasses.RecipeStepAndIngredientsList
import com.example.mrcooker.data.utils.parseStringFromDatabase
import com.example.mrcooker.data.utils.recepts.RecipeForCooking
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewAllDishViewModel @Inject constructor(private val application: Application) : ViewModel() {

    val database: CookerDatabase = CookerDatabase.getDatabase(application)

    fun getDishList(sharedPref: SharedPreferences?): MutableList<RecipeStepAndIngredientsList> {
        val result = mutableListOf<RecipeStepAndIngredientsList>()
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
                Log.d("fdgdfgdg", i)
                cocingStepList.add(CookingStep(i, 0, false, dish.dishName + dish.rate))
            }

            result.add(RecipeStepAndIngredientsList(ingredientsList, cocingStepList, dishData))
        }
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

        result.add(meetPee)
        result.add(soup)
        result.add(potatoRecipe)
        result.add(pastaRecipe)
        return result
    }
}