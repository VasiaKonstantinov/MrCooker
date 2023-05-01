package com.example.mrcooker.data.utils.recepts

import android.content.Context
import android.content.SharedPreferences
import com.example.mrcooker.R
import com.example.mrcooker.data.utils.*
import com.example.mrcooker.data.utils.dataClasses.CookingStep
import com.example.mrcooker.data.utils.dataClasses.DishData
import com.example.mrcooker.data.utils.dataClasses.Ingredients
import com.example.mrcooker.data.utils.dataClasses.RecipeStepAndIngredientsList
import com.example.mrcooker.data.utils.enumClasses.Products
import dagger.hilt.android.qualifiers.ApplicationContext

class RecipeForCooking(
    @ApplicationContext private val context: Context,
    private val sharedPref: SharedPreferences
) {

    fun createFriedPotatoesRecipe(): RecipeStepAndIngredientsList {
        val isRedListKey = OVEN_BAKED_POTATO_FRIES + IS_RED_LIST
        val rateKey = OVEN_BAKED_POTATO_FRIES + RATE
        val isRedList = sharedPref.getBoolean(isRedListKey, false)
        val rate = sharedPref.getFloat(rateKey, 0f)

        val ingredientsList = mutableListOf<Ingredients>()
        ingredientsList.add(Ingredients(Products.Potatoes.name))
//        ingredientsList.add(Ingredients(context.getString(R.string.text_ovenBakedPotatoFries_first_ing)))
//        ingredientsList.add(Ingredients(context.getString(R.string.text_ovenBakedPotatoFries_second_ing)))
//        ingredientsList.add(Ingredients(context.getString(R.string.text_ovenBakedPotatoFries_third_ing)))
//        ingredientsList.add(Ingredients(context.getString(R.string.text_ovenBakedPotatoFries_fourth_ing)))
//        ingredientsList.add(Ingredients(context.getString(R.string.text_ovenBakedPotatoFries_first_ing)))
//        ingredientsList.add(Ingredients(context.getString(R.string.text_ovenBakedPotatoFries_sixth_ing)))

        val stepsList = mutableListOf<CookingStep>()
        stepsList.add(
            CookingStep(
                context.getString(R.string.text_ovenBakedPotatoFries_first_step),
                0,
                rateKey = rateKey
            )
        )
        stepsList.add(
            CookingStep(
                context.getString(R.string.text_ovenBakedPotatoFries_second_step),
                0,
                rateKey = rateKey
            )
        )
        stepsList.add(
            CookingStep(
                context.getString(R.string.text_ovenBakedPotatoFries_third_step),
                450,
                rateKey = rateKey
            )
        )
        return RecipeStepAndIngredientsList(
            ingredientsList,
            stepsList,
            DishData(OVEN_BAKED_POTATO_FRIES, rate, rateKey, isRedList, isRedListKey)
        )
    }

    fun createPastaRecipe(): RecipeStepAndIngredientsList {
        val isRedListKey = SPAGHETTI_AGLIO_E_OLIO + IS_RED_LIST
        val rateKey = SPAGHETTI_AGLIO_E_OLIO + RATE
        val isRedList = sharedPref.getBoolean(isRedListKey, false)
        val rate = sharedPref.getFloat(rateKey, 0f)

        val ingredientsList = mutableListOf<Ingredients>()
        ingredientsList.add(Ingredients(Products.Garlic.name))
        ingredientsList.add(Ingredients(Products.Pasta.name))
        ingredientsList.add(Ingredients(Products.OliveOil.name))
        ingredientsList.add(Ingredients(Products.Cheese.name))

        val stepsList = mutableListOf<CookingStep>()

        stepsList.add(
            CookingStep(
                context.getString(R.string.text_SPAGHETTI_AGLIO_E_OLIO_first_step),
                0,
                rateKey = rateKey
            )
        )
        stepsList.add(
            CookingStep(
                context.getString(R.string.text_SPAGHETTI_AGLIO_E_OLIO_second_step),
                0,
                rateKey = rateKey
            )
        )
        stepsList.add(
            CookingStep(
                context.getString(R.string.text_SPAGHETTI_AGLIO_E_OLIO_third_step),
                0,
                rateKey = rateKey
            )
        )
        stepsList.add(
            CookingStep(
                context.getString(R.string.text_SPAGHETTI_AGLIO_E_OLIO_four_step),
                0,
                rateKey = rateKey
            )
        )

        return RecipeStepAndIngredientsList(
            ingredientsList,
            stepsList,
            DishData(SPAGHETTI_AGLIO_E_OLIO, rate, rateKey, isRedList, isRedListKey)
        )
    }

    fun createQuickAndEasyChickenNoodleSoup(): RecipeStepAndIngredientsList {
        val isRedListKey = QUICK_AND_EASY_CHICKEN_MOODLE_SOUP + IS_RED_LIST
        val rateKey = QUICK_AND_EASY_CHICKEN_MOODLE_SOUP + RATE
        val isRedList = sharedPref.getBoolean(isRedListKey, false)
        val rate = sharedPref.getFloat(rateKey, 0f)

        val ingredientsList = mutableListOf<Ingredients>()
        ingredientsList.add(Ingredients(Products.Noodles.name))
        ingredientsList.add(Ingredients(Products.Chicken.name))
        ingredientsList.add(Ingredients(Products.Carrot.name))
        ingredientsList.add(Ingredients(Products.Onions.name))

        val stepsList = mutableListOf<CookingStep>()

        stepsList.add(
            CookingStep(
                context.getString(R.string.text_QUICK_AND_EASY_CHICKEN_MOODLE_SOUP_first_step),
                0,
                rateKey = rateKey
            )
        )
        stepsList.add(
            CookingStep(
                context.getString(R.string.text_QUICK_AND_EASY_CHICKEN_MOODLE_SOUP_second_step),
                0,
                rateKey = rateKey
            )
        )
        stepsList.add(
            CookingStep(
                context.getString(R.string.text_QUICK_AND_EASY_CHICKEN_MOODLE_SOUP_third_step),
                0,
                rateKey = rateKey
            )
        )

        return RecipeStepAndIngredientsList(
            ingredientsList,
            stepsList,
            DishData(QUICK_AND_EASY_CHICKEN_MOODLE_SOUP, rate, rateKey, isRedList, isRedListKey)
        )
    }

    fun createMeatPie(): RecipeStepAndIngredientsList {
        val isRedListKey = MEAT_PIE + IS_RED_LIST
        val rateKey = MEAT_PIE + RATE
        val isRedList = sharedPref.getBoolean(isRedListKey, false)
        val rate = sharedPref.getFloat(rateKey, 0f)

        val ingredientsList = mutableListOf<Ingredients>()
        ingredientsList.add(Ingredients(Products.GroundBeef.name))
        ingredientsList.add(Ingredients(Products.CakesForPee.name))
        ingredientsList.add(Ingredients(Products.Garlic.name))
        ingredientsList.add(Ingredients(Products.Onions.name))

        val stepsList = mutableListOf<CookingStep>()

        stepsList.add(
            CookingStep(
                context.getString(R.string.text_MEAT_PIE_first_step),
                0,
                rateKey = rateKey
            )
        )
        stepsList.add(
            CookingStep(
                context.getString(R.string.text_MEAT_PIE_second_step),
                0,
                rateKey = rateKey
            )
        )
        stepsList.add(
            CookingStep(
                context.getString(R.string.text_MEAT_PIE_third_step),
                0,
                rateKey = rateKey
            )
        )
        stepsList.add(
            CookingStep(
                context.getString(R.string.text_MEAT_PIE_four_step),
                0,
                rateKey = rateKey
            )
        )
        stepsList.add(
            CookingStep(
                context.getString(R.string.text_MEAT_PIE_five_step),
                0,
                rateKey = rateKey
            )
        )
        stepsList.add(
            CookingStep(
                context.getString(R.string.text_MEAT_PIE_sex_step),
                0,
                rateKey = rateKey
            )
        )

        return RecipeStepAndIngredientsList(
            ingredientsList,
            stepsList,
            DishData(MEAT_PIE, rate, rateKey, isRedList, isRedListKey)
        )
    }
}