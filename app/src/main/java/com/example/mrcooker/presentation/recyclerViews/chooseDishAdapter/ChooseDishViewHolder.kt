package com.example.mrcooker.presentation.recyclerViews.chooseDishAdapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.mrcooker.data.utils.dataClasses.RecipeStepAndIngredientsList
import com.example.mrcooker.databinding.DishItemBinding

class ChooseDishViewHolder(
    private val binding: DishItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private val context = itemView.context

    fun onBind(
        recipeStepAndIngredientsList: RecipeStepAndIngredientsList,
        onItemClick: ((RecipeStepAndIngredientsList) -> Unit)
    ) {
        with(binding) {
            cbProduct.text = recipeStepAndIngredientsList.dishData.name
            rateBar.rating = recipeStepAndIngredientsList.dishData.rate
            rateBar.isEnabled = false

            root.setOnClickListener {
                onItemClick(recipeStepAndIngredientsList)
            }
        }
    }
}