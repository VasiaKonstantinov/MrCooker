package com.example.mrcooker.presentation.recyclerViews.redProductsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mrcooker.data.room.RedProduct
import com.example.mrcooker.databinding.RedProductItemBinding

class RedProductAdapter :
    ListAdapter<RedProduct, RedProductViewHolder>(RedProductsDiffCallback()) {

    lateinit var onItemClick: ((RedProduct) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedProductViewHolder {
        val binding = RedProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return RedProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RedProductViewHolder, position: Int) {
        holder.onBind(currentList[position], onItemClick)
    }
}