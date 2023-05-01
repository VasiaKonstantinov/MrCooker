package com.example.mrcooker.presentation.recyclerViews.viewPagerAdapter

import android.os.Bundle
import android.util.Log
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mrcooker.data.room.CookerDatabase
import com.example.mrcooker.data.utils.IS_LAST_STEP
import com.example.mrcooker.data.utils.RECIPE_STEP_KEY
import com.example.mrcooker.data.utils.dataClasses.RecipeStepAndIngredientsList
import com.example.mrcooker.presentation.fragments.cookingStepsFragment.CookingStepFragment

class ViewPagerAdapter(
    fragmentActivity: Fragment,
    private val recipeStepAndIngredientsList: RecipeStepAndIngredientsList
) : FragmentStateAdapter(fragmentActivity) {

    val database = CookerDatabase.getDatabase(fragmentActivity.requireContext())

    override fun getItemCount(): Int {
        return recipeStepAndIngredientsList.cokingStepList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = CookingStepFragment()
        fragment.arguments = Bundle().apply {
            val step = recipeStepAndIngredientsList.cokingStepList[position]
            step.rateKey = recipeStepAndIngredientsList.cokingStepList[position].rateKey
            if (position == recipeStepAndIngredientsList.cokingStepList.size - 1) {
                step.isLastStep = true
            }
            putParcelable(RECIPE_STEP_KEY, recipeStepAndIngredientsList.cokingStepList[position])
            //   putBoolean(IS_LAST_STEP, recipeStepAndIngredientsList.cokingStepList.size == position)
        }
        return fragment
    }
}