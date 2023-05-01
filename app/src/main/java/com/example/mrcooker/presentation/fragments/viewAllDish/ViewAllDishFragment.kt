package com.example.mrcooker.presentation.fragments.viewAllDish

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrcooker.R
import com.example.mrcooker.data.utils.dataClasses.RecipeStepAndIngredientsList
import com.example.mrcooker.databinding.ChooseProductFragmentBinding
import com.example.mrcooker.presentation.recyclerViews.chooseDishAdapter.ChooseDishAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewAllDishFragment : Fragment() {

    private lateinit var binding: ChooseProductFragmentBinding
    private val viewModel by viewModels<ViewAllDishViewModel>()
    private var sharedPref: SharedPreferences? = null

    private val chooseDishAdapter = ChooseDishAdapter()
    private var dishList = mutableListOf<RecipeStepAndIngredientsList>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChooseProductFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        sharedPref = activity?.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        dishList = viewModel.getDishList(sharedPref)
        with(binding) {
            rvProducts.layoutManager = LinearLayoutManager(requireContext())
            chooseDishAdapter.submitList(dishList)
            rvProducts.adapter = chooseDishAdapter
            processRecyclerViewItemClickListener()
            btnViewAllDishes.visibility = View.GONE
            btnCreateDish.visibility = View.GONE
            btnShowRedList.visibility = View.GONE
            btnChooseProducts.visibility = View.GONE
        }
    }

    private fun processRecyclerViewItemClickListener() {
        chooseDishAdapter.onItemClick = { recipeStepAndIngredientsList ->
            startViewPagerFragment(recipeStepAndIngredientsList)
        }
    }

    private fun startViewPagerFragment(recipeStepAndIngredientsList: RecipeStepAndIngredientsList) {
        findNavController().navigate(
            ViewAllDishFragmentDirections.actionViewAllDishFragmentToCookingViewPagerFragment(
                recipeStepAndIngredientsList
            )
        )
    }

    override fun onPause() {
        super.onPause()
        dishList.clear()
    }
}