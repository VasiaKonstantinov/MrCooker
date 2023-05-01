package com.example.mrcooker.presentation.recyclerViews.chooseDishAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mrcooker.data.utils.dataClasses.RecipeStepAndIngredientsList
import com.example.mrcooker.databinding.DishItemBinding

class ChooseDishAdapter :
    ListAdapter<RecipeStepAndIngredientsList, ChooseDishViewHolder>(ChooseDishDiffCallback()) {

    lateinit var onItemClick: ((RecipeStepAndIngredientsList) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseDishViewHolder {
        val binding = DishItemBinding.inflate(LayoutInflater.from(parent.context))
        return ChooseDishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChooseDishViewHolder, position: Int) {
        holder.onBind(currentList[position], onItemClick)
    }
}