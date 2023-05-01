package com.example.mrcooker.presentation.fragments.redProductListFragment

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
import com.example.mrcooker.data.room.RedProduct
import com.example.mrcooker.data.utils.dataClasses.ProductData
import com.example.mrcooker.databinding.ChooseProductFragmentBinding
import com.example.mrcooker.presentation.dialogs.AddProductInRedListDialog
import com.example.mrcooker.presentation.dialogs.RemoveProductFromRedListDialog
import com.example.mrcooker.presentation.dialogs.SetDishRateDialog
import com.example.mrcooker.presentation.fragments.redProductListFragment.RedFragmentProductsViewModel
import com.example.mrcooker.presentation.recyclerViews.chooseProductsAdapter.ChooseProductsAdapter
import com.example.mrcooker.presentation.recyclerViews.redProductsAdapter.RedProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RedProductsFragment @Inject constructor() : Fragment() {

    private lateinit var binding: ChooseProductFragmentBinding
    private val viewModel by viewModels<RedFragmentProductsViewModel>()

    private val redProductsAdapter = RedProductAdapter()
    private var redProductsList: List<RedProduct>? = null

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
        redProductsList = viewModel.getRedListProducts()
        binding.btnViewAllDishes.visibility = View.GONE
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding) {
            rvProducts.layoutManager = LinearLayoutManager(requireContext())
            if (redProductsList?.isEmpty() == true) createToast("Red list is empty")
            redProductsAdapter.submitList(redProductsList)
            rvProducts.adapter = redProductsAdapter
            processRecyclerViewItemClickListener()
            binding.btnShowRedList.visibility = View.GONE
            binding.btnCreateDish.visibility = View.GONE
            binding.btnChooseProducts.visibility = View.GONE
        }
    }

    private fun processRecyclerViewItemClickListener() {
        redProductsAdapter.onItemClick = { redProduct ->
            startRemoveProductInRedListDialog(redProduct)
        }
    }

    private fun startRemoveProductInRedListDialog(redProduct: RedProduct) {
        val fragment = RemoveProductFromRedListDialog.newInstance()

        fragment.onRemoveProductInRedListListener =
            object : RemoveProductFromRedListDialog.OnRemoveProductInRedListListener {
                override fun onRemoveProductInRedList(result: Boolean) {
                    if (result) {
                        viewModel.deleteRedProduct(redProduct)
                        updateRecyclerViewList(redProduct)
                        createToast("Product was removed from list")
                    } else createToast("Product was don't removed from list")
                }
            }
        fragment.show(requireActivity().supportFragmentManager, "DialogAddProductInRedList")
    }

    private fun updateRecyclerViewList(redProduct: RedProduct) {
//        val newList = redProductsList
//        newList?.toMutableList()?.remove(redProduct)
//        newList?.forEachIndexed { index, redProductFromList ->
//        }
//        mustBeRemovedElementPosition?.let {
//            newList?.toMutableList()?.removeAt(it)
//        }
//        Log.d("fgdgfdgd", newList.toString())
//        Log.d("fgdgfdgd", redProduct.toString())
//        Log.d("fgdgfdgd", mustBeRemovedElementPosition.toString())

        var mustBeRemovedElementPosition: Int? = null
        redProductsList?.toList()
        redProductsList?.forEachIndexed { index, redProductFromList ->
            if (redProduct.id == redProductFromList.id) mustBeRemovedElementPosition = index
        }
        mustBeRemovedElementPosition?.let {
            redProductsList?.toMutableList()?.removeAt(it)
        }
        redProductsAdapter.submitList(redProductsList?.toList())
    }

    private fun createToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}