package com.example.mrcooker.presentation.recyclerViews.drowableRecyclerView

import androidx.recyclerview.widget.RecyclerView
import com.example.mrcooker.data.utils.dataClasses.ProductData
import com.example.mrcooker.databinding.ProductItemBinding

class DrowableViewHolder(
    private val binding: ProductItemBinding,
    private val onItemClick: ((ProductData) -> Unit)
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        productData: ProductData,

        ) {
        with(binding) {
            cbProduct.text = productData.productName
            cbProduct.isChecked = productData.selected
            cbProduct.setOnClickListener {
                productData.selected = !productData.selected
                onItemClick(productData)
            }
        }
    }
}