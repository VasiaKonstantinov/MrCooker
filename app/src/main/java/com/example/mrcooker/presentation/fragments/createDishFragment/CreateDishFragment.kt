package com.example.mrcooker.presentation.fragments.createDishFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrcooker.data.utils.dataClasses.ProductData
import com.example.mrcooker.data.utils.dataClasses.Steps
import com.example.mrcooker.data.utils.enumClasses.Products
import com.example.mrcooker.databinding.CreateDishFragmentBinding
import com.example.mrcooker.presentation.fragments.chooseProductsListFragment.ChooseProductsFragmentViewModel
import com.example.mrcooker.presentation.recyclerViews.chooseProductsAdapter.ChooseProductsAdapter
import com.example.mrcooker.presentation.recyclerViews.drowableRecyclerView.DrowableAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateDishFragment @Inject constructor() : Fragment() {

    private lateinit var binding: CreateDishFragmentBinding
    private val viewModel by viewModels<CreateDishFragmentViewModel>()
    private val productList: List<ProductData>? = null

    private var chooseProductsAdapter: DrowableAdapter? = null
    var step = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CreateDishFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        with(binding) {
            etProducts.setOnClickListener {
                if (frameProducts.visibility == View.VISIBLE) {
                    etProducts.hint = "Tab to select product"
                    frameProducts.visibility = View.GONE
                } else {
                    etProducts.hint = "Tab to hide list"
                    frameProducts.visibility = View.VISIBLE
                }
            }
            btnCreateStep.setOnClickListener {
                if (etSteps.text?.isNotEmpty() == true) {
                    var stepTimer = 0
                    if (etStepsTimer.text?.isNotEmpty() == true) stepTimer =
                        etStepsTimer.text.toString().toInt()

                    viewModel.addStep(
                        Steps(
                            etSteps.text.toString(),
                            stepTimer
                        )
                    )
                    step++
                    createToast("Step is created")
                } else {
                    createToast("Discription is empty")
                }

                if (etSteps.text?.isNotEmpty() == true) {
                    var stepTimer = 0
                    if (etStepsTimer.text?.isNotEmpty() == true) stepTimer =
                        etStepsTimer.text.toString().toInt()
                    viewModel.timerTime = stepTimer
                }
            }
            btnCreateDish.setOnClickListener {
                if (etDishName.text?.isEmpty() == true) {
                    createToast("Dish name is empty")
                } else {
                    viewModel.createDish(etDishName.text.toString())
                    step = 0
                    createToast("Dish is created")
                }
            }
        }
    }

    private fun initRecyclerView() {
        processRecyclerViewItemClickListener()
        val productList =
            viewModel.createProductListWithoutRedProducts(viewModel.createProductDataList())

        with(binding) {
            rvProducts.layoutManager = LinearLayoutManager(requireContext())
            chooseProductsAdapter?.submitList(productList)
            rvProducts.adapter = chooseProductsAdapter
            processRecyclerViewItemClickListener()
        }
    }

    private fun processRecyclerViewItemClickListener() {
        chooseProductsAdapter = DrowableAdapter { productData ->
            if (productData.selected) viewModel.addItemInSelectedProductsList(productData)
            else viewModel.deleteItemInSelectedProductList(productData)
        }
    }

    private fun createToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}