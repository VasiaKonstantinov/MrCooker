package com.example.mrcooker.presentation.fragments.bookListFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrcooker.data.room.BookEntity
import com.example.mrcooker.databinding.BookListFragmentBinding
import com.example.mrcooker.presentation.dialogs.AddBookDialog
import com.example.mrcooker.presentation.recyclerViews.bookAdapter.BookAdapter
import com.example.mrcooker.presentation.recyclerViews.chooseDishAdapter.ChooseDishAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BookListFragment @Inject constructor() : Fragment() {

    private lateinit var binding: BookListFragmentBinding
    private val viewModel by viewModels<BookListViewModel>()
    private var bookAdapter: BookAdapter? = null

    private var sortedType = SORTED_BY_NAME

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BookListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        binding.btnAddBook.setOnClickListener {
           startAddBookDialog()
        }
        binding.btnSortBy.setOnClickListener {
            if (sortedType == SORTED_BY_DATE_OF_MANUFACTURE) sortedType = SORTED_BY_AUTHOR
            bookAdapter?.submitList(viewModel.getSortedList(sortedType).toList())
            sortedType++
        }
    }

    private fun initRecyclerView() {
        with(binding) {
            bookAdapter = BookAdapter {
                viewModel.removeBook(it)
                bookAdapter?.submitList(viewModel.getAllBooks())
            }
            rvBooks.layoutManager = LinearLayoutManager(requireContext())
            bookAdapter?.submitList(viewModel.getAllBooks())
            rvBooks.adapter = bookAdapter
        }
    }
    
    private fun startAddBookDialog() {
        val fragment = AddBookDialog.newInstance()

        fragment.onAddBookListener = object : AddBookDialog.OnAddBookListener {
            override fun onAddBookListener(bookEntity: BookEntity) {
                viewModel.addBook(bookEntity)
                bookAdapter?.submitList(viewModel.getAllBooks())
            }
        }
        fragment.show(requireActivity().supportFragmentManager, "DialogAddProductInRedList")
    }

    companion object {
        const val SORTED_BY_AUTHOR = 0
        const val SORTED_BY_ID = 1
        const val SORTED_BY_NAME = 2
        const val SORTED_BY_DATE_OF_MANUFACTURE = 3
    }
}