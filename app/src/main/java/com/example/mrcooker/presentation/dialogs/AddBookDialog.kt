package com.example.mrcooker.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.mrcooker.R
import com.example.mrcooker.data.room.BookEntity
import com.example.mrcooker.data.utils.viewBinding
import com.example.mrcooker.databinding.CreateBookDialogBinding

class AddBookDialog : DialogFragment(R.layout.create_book_dialog) {

    private val binding by viewBinding(CreateBookDialogBinding::bind)
    var onAddBookListener: OnAddBookListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(R.drawable.popup_background)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnAddBook.setOnClickListener {
                if (etAuthor.text.isNullOrBlank() || etBookName.text.isNullOrBlank() || etDateOfManufacture.text.isNullOrBlank()) {
                    createToast("All fields must be filled")
                } else {
                    createToast("Book added")
                    onAddBookListener?.onAddBookListener(
                        BookEntity(
                            name = etBookName.text.toString(),
                            author = etAuthor.text.toString(),
                            dateOfManufacture = etDateOfManufacture.text.toString().toInt()
                        )
                    )
                    dismiss()
                }
            }
        }
    }

    private fun createToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    interface OnAddBookListener {
        fun onAddBookListener(bookEntity: BookEntity)
    }

    companion object {
        fun newInstance(): AddBookDialog = AddBookDialog()
    }
}