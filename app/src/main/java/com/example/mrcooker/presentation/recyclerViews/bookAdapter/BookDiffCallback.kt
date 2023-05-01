package com.example.mrcooker.presentation.recyclerViews.bookAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.mrcooker.data.room.BookEntity

class BookDiffCallback : DiffUtil.ItemCallback<BookEntity>() {

    override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean =
        oldItem.id == newItem.id
}