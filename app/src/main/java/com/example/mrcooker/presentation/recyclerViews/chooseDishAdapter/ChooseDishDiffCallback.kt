package com.example.mrcooker.presentation.recyclerViews.chooseDishAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.mrcooker.data.utils.dataClasses.RecipeStepAndIngredientsList

class ChooseDishDiffCallback : DiffUtil.ItemCallback<RecipeStepAndIngredientsList>() {
    override fun areItemsTheSame(
        oldItem: RecipeStepAndIngredientsList,
        newItem: RecipeStepAndIngredientsList
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: RecipeStepAndIngredientsList,
        newItem: RecipeStepAndIngredientsList
    ): Boolean {
        return oldItem.dishData.name == newItem.dishData.name
    }
}