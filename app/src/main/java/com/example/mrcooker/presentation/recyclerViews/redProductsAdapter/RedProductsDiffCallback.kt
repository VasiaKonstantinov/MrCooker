package com.example.mrcooker.presentation.recyclerViews.redProductsAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.mrcooker.data.room.RedProduct

class RedProductsDiffCallback : DiffUtil.ItemCallback<RedProduct>() {
    override fun areItemsTheSame(oldItem: RedProduct, newItem: RedProduct): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: RedProduct, newItem: RedProduct): Boolean =
        oldItem.id == newItem.id
}