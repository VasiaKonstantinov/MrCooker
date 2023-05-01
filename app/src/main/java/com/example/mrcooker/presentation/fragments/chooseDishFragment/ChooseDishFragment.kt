package com.example.mrcooker.presentation.fragments.chooseDishFragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrcooker.R
import com.example.mrcooker.data.utils.dataClasses.ProductData
import com.example.mrcooker.data.utils.dataClasses.RecipeStepAndIngredientsList
import com.example.mrcooker.databinding.ChooseProductFragmentBinding
import com.example.mrcooker.presentation.recyclerViews.chooseDishAdapter.ChooseDishAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseDishFragment @Inject constructor() : Fragment() {

    private lateinit var binding: ChooseProductFragmentBinding
    private val viewModel by viewModels<ChooseDishFragmentViewModel>()
    private val args by navArgs<ChooseDishFragmentArgs>()
    private var selectedProductsList: Array<ProductData>? = null
    private var sharedPref: SharedPreferences? = null

    private val chooseDishAdapter = ChooseDishAdapter()
    private var dishList = mutableListOf<RecipeStepAndIngredientsList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedProductsList = args.productDataList
    }

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
        sharedPref = activity?.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        binding.btnViewAllDishes.visibility = View.GONE
        binding.btnShowRedList.visibility = View.GONE
        createDishList()
        initRecyclerView()
        with(binding) {
            btnChooseProducts.visibility = View.GONE
            btnCreateDish.visibility = View.GONE
        }
    }

    private fun initRecyclerView() {
        with(binding) {
            rvProducts.layoutManager = LinearLayoutManager(requireContext())
            chooseDishAdapter.submitList(dishList)
            rvProducts.adapter = chooseDishAdapter
            processRecyclerViewItemClickListener()
        }
    }

    private fun processRecyclerViewItemClickListener() {
        chooseDishAdapter.onItemClick = { recipeStepAndIngredientsList ->
            startViewPagerFragment(recipeStepAndIngredientsList)
        }
    }

    private fun createDishList() {
        selectedProductsList?.let { dishList = viewModel.getDishListByProducts(it, sharedPref) }
    }

    private fun startViewPagerFragment(recipeStepAndIngredientsList: RecipeStepAndIngredientsList) {
        findNavController().navigate(
            ChooseDishFragmentDirections.actionChooseDishFragmentToCookingViewPagerFragment(
                recipeStepAndIngredientsList
            )
        )
    }

    override fun onPause() {
        super.onPause()
        dishList.clear()
    }
}