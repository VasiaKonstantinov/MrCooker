package com.example.mrcooker.presentation.recyclerViews.chooseProductsAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mrcooker.data.utils.dataClasses.ProductData
import com.example.mrcooker.databinding.ProductItemBinding

class ChooseProductsAdapter :
    ListAdapter<ProductData, ChooseProductsViewHolder>(ChooseProductsDiffCallback()) {

    lateinit var onItemClick: ((ProductData) -> Unit)
    lateinit var onLongClick: ((ProductData) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseProductsViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ChooseProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChooseProductsViewHolder, position: Int) {
        holder.onBind(currentList[position], onItemClick, onLongClick)
    }
}