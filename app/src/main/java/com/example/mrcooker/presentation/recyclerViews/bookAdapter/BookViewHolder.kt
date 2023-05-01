package com.example.mrcooker.presentation.recyclerViews.bookAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.mrcooker.data.room.BookEntity
import com.example.mrcooker.data.utils.dataClasses.ProductData
import com.example.mrcooker.databinding.BookItemBinding

class BookViewHolder(private val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        bookEntity: BookEntity,
        onLongClick: ((BookEntity) -> Unit)
    ) {
        with(binding) {
            number.text = bookEntity.id.toString()
            dateOfManufacture.text = bookEntity.dateOfManufacture.toString()
            bookName.text = bookEntity.name
            author.text = bookEntity.author
            root.setOnLongClickListener {
                onLongClick(bookEntity)
                true
            }
        }
    }
}