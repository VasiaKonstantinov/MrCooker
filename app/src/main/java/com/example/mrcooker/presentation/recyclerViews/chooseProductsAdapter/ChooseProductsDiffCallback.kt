package com.example.mrcooker.presentation.recyclerViews.chooseProductsAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.mrcooker.data.utils.dataClasses.ProductData

class ChooseProductsDiffCallback: DiffUtil.ItemCallback<ProductData>() {
    override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
        return oldItem.productName == newItem.productName
    }
}