package com.example.mrcooker.presentation.recyclerViews.drowableRecyclerView

import androidx.recyclerview.widget.DiffUtil
import com.example.mrcooker.data.utils.dataClasses.ProductData

class DrowableDiffCallback : DiffUtil.ItemCallback<ProductData>() {
    override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
        return oldItem.productName == newItem.productName
    }
}