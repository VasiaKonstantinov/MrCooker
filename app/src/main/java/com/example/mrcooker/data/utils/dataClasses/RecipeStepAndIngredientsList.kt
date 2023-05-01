package com.example.mrcooker.data.utils.dataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeStepAndIngredientsList(
    val ingredientsList: List<Ingredients>,
    val cokingStepList: List<CookingStep>,
    val dishData: DishData
): Parcelable