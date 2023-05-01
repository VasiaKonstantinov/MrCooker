package com.example.mrcooker.presentation.recyclerViews.bookAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.mrcooker.data.room.BookEntity
import com.example.mrcooker.data.utils.dataClasses.ProductData
import com.example.mrcooker.databinding.BookItemBinding
import com.example.mrcooker.databinding.DishItemBinding
import com.example.mrcooker.presentation.fragments.bookListFragment.BookListViewModel
import com.example.mrcooker.presentation.recyclerViews.chooseDishAdapter.ChooseDishViewHolder
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent

class BookAdapter(private val onLongClick: ((BookEntity) -> Unit)) :
    ListAdapter<BookEntity, BookViewHolder>(BookDiffCallback()) {

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.onBind(currentList[position], onLongClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding =
            BookItemBinding.inflate(LayoutInflater.from(parent.context))
        return BookViewHolder(binding)
    }
}