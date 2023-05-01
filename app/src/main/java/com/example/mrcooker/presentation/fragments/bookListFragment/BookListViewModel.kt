package com.example.mrcooker.presentation.fragments.bookListFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.mrcooker.data.room.BookEntity
import com.example.mrcooker.data.room.CookerDatabase
import com.example.mrcooker.presentation.fragments.bookListFragment.BookListFragment.Companion.SORTED_BY_AUTHOR
import com.example.mrcooker.presentation.fragments.bookListFragment.BookListFragment.Companion.SORTED_BY_DATE_OF_MANUFACTURE
import com.example.mrcooker.presentation.fragments.bookListFragment.BookListFragment.Companion.SORTED_BY_ID
import com.example.mrcooker.presentation.fragments.bookListFragment.BookListFragment.Companion.SORTED_BY_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(application: Application) : ViewModel() {

    private val database: CookerDatabase = CookerDatabase.getDatabase(application)

    fun addBook(bookEntity: BookEntity) = database.libraryDatabaseDao().insertBook(bookEntity)
    fun getAllBooks(): List<BookEntity> = database.libraryDatabaseDao().getAll()
    fun removeBook(bookEntity: BookEntity) = database.libraryDatabaseDao().removeBook(bookEntity)

    fun getSortedList(sortedByType: Int): List<BookEntity> {
        val booksList = getAllBooks().toMutableList()
        return when (sortedByType) {
            SORTED_BY_ID -> booksList.sortedBy { it.id }
            SORTED_BY_AUTHOR -> booksList.sortedBy { it.author }
            SORTED_BY_NAME -> booksList.sortedBy { it.name }
            SORTED_BY_DATE_OF_MANUFACTURE -> booksList.sortedBy { it.dateOfManufacture }
            else -> booksList.sortedBy { it.id }
        }
    }
}