package com.example.mrcooker.presentation.recyclerViews.chooseProductsAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.mrcooker.data.utils.dataClasses.ProductData
import com.example.mrcooker.databinding.ProductItemBinding

class ChooseProductsViewHolder(
    private val binding: ProductItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        productData: ProductData,
        onItemClick: ((ProductData) -> Unit),
        onLongClick: ((ProductData) -> Unit)
    ) {
        with(binding) {
            cbProduct.text = productData.productName
            cbProduct.isChecked = productData.selected
            cbProduct.setOnClickListener {
                productData.selected = !productData.selected
                onItemClick(productData)
            }
            cbProduct.setOnLongClickListener {
                onLongClick(productData)
                true
            }
        }
    }
}