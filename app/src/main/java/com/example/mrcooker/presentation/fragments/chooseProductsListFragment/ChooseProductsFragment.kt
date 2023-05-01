package com.example.mrcooker.presentation.fragments.chooseProductsListFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrcooker.data.utils.dataClasses.ProductData
import com.example.mrcooker.databinding.ChooseProductFragmentBinding
import com.example.mrcooker.presentation.dialogs.AddProductInRedListDialog
import com.example.mrcooker.presentation.dialogs.SetDishRateDialog
import com.example.mrcooker.presentation.fragments.chooseDishFragment.ChooseDishFragmentDirections
import com.example.mrcooker.presentation.recyclerViews.chooseProductsAdapter.ChooseProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseProductsFragment @Inject constructor() : Fragment() {

    private lateinit var binding: ChooseProductFragmentBinding
    private val viewModel by viewModels<ChooseProductsFragmentViewModel>()

    private val chooseProductsAdapter = ChooseProductsAdapter()

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
        with(binding) {
            btnChooseProducts.setOnClickListener {
                startChooseDishFragment()
                viewModel.clearSelectedProductsList()
            }
            binding.btnViewAllDishes.visibility = View.VISIBLE
            binding.btnViewAllDishes.setOnClickListener {
                startAllDishFragment()
            }
            btnShowRedList.visibility = View.VISIBLE
            btnShowRedList.setOnClickListener {
                startRedListFragment()
            }
            btnCreateDish.setOnClickListener {
                startCreateDishFragment()
            }
            tvHeader.visibility = View.VISIBLE
            divider.visibility = View.VISIBLE
        }
    }

    private fun initRecyclerView() {
        val productList =
            viewModel.createProductListWithoutRedProducts(viewModel.createProductDataList())

        with(binding) {
            rvProducts.layoutManager = LinearLayoutManager(requireContext())
            chooseProductsAdapter.submitList(productList)
            rvProducts.adapter = chooseProductsAdapter
            processRecyclerViewItemClickListener()
        }
    }

    private fun processRecyclerViewItemClickListener() {
        chooseProductsAdapter.onItemClick = { productData ->
            if (productData.selected) viewModel.addItemInSelectedProductsList(productData)
            else viewModel.deleteItemInSelectedProductList(productData)
        }
        chooseProductsAdapter.onLongClick = { productData ->
            startAddProductInRedListDialog(productData)
        }
    }

    private fun startChooseDishFragment() {
        val action =
            ChooseProductsFragmentDirections.actionChooseProductsFragmentToChooseDishFragment(
                viewModel.getSelectedProductsList().toTypedArray()
            )
        findNavController().navigate(action)
    }

    private fun startRedListFragment() {
        val action =
            ChooseProductsFragmentDirections.actionChooseProductsFragmentToRedProductsFragment()
        findNavController().navigate(action)
    }

    private fun startCreateDishFragment() {
        val action =
            ChooseProductsFragmentDirections.actionChooseProductsFragmentToCreateDishFragment()
        findNavController().navigate(action)
    }

    private fun startAddProductInRedListDialog(productData: ProductData) {
        val fragment = AddProductInRedListDialog.newInstance()

        fragment.onAddProductInRedListListener =
            object : AddProductInRedListDialog.OnAddProductInRedListListener {
                override fun onAddProductInRedList(result: Boolean) {
                    if (result) {
                        viewModel.insertRedProduct(productData)
                        createToast("Product added in red list")
                    } else createToast("Add to product in red list is not allowed")
                }
            }
        fragment.show(requireActivity().supportFragmentManager, "DialogAddProductInRedList")
    }

    private fun startAllDishFragment(){
        val action = ChooseProductsFragmentDirections.actionChooseProductsFragmentToViewAllDishFragment()
        findNavController().navigate(action)
    }

    private fun createToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}