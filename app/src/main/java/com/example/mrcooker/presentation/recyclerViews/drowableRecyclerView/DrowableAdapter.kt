package com.example.mrcooker.presentation.recyclerViews.drowableRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mrcooker.data.utils.dataClasses.ProductData
import com.example.mrcooker.databinding.ProductItemBinding

class DrowableAdapter(private val onItemClick: ((ProductData) -> Unit)) :
    ListAdapter<ProductData, DrowableViewHolder>(DrowableDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrowableViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return DrowableViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: DrowableViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}