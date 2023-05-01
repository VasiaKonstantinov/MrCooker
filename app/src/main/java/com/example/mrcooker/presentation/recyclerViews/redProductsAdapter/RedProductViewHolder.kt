package com.example.mrcooker.presentation.recyclerViews.redProductsAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.mrcooker.data.room.RedProduct
import com.example.mrcooker.databinding.RedProductItemBinding

class RedProductViewHolder(private val binding: RedProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(redProduct: RedProduct, onItemClick: ((RedProduct) -> Unit)) {
        with(binding) {
            tvProduct.text = redProduct.productName
            tvProduct.setOnClickListener {
                onItemClick(redProduct)
            }
        }
    }
}