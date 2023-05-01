package com.example.mrcooker.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.mrcooker.R
import com.example.mrcooker.data.utils.viewBinding
import com.example.mrcooker.databinding.AddOrRemoveProductInRedListDialogBinding

class RemoveProductFromRedListDialog: DialogFragment(R.layout.add_or_remove_product_in_red_list_dialog) {

    private val binding by viewBinding(AddOrRemoveProductInRedListDialogBinding::bind)
    var onRemoveProductInRedListListener: OnRemoveProductInRedListListener? = null

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
        binding.tvAddToRedList.text = "Remove product from red list?"
        binding.btnAddToRedList.setOnClickListener {
            onRemoveProductInRedListListener?.onRemoveProductInRedList(true)
            dismiss()
        }
        binding.btnDontAddToRedList.setOnClickListener {
            onRemoveProductInRedListListener?.onRemoveProductInRedList(false)
            dismiss()
        }
    }

    interface OnRemoveProductInRedListListener {
        fun onRemoveProductInRedList(result: Boolean)
    }

    companion object {
        fun newInstance(): RemoveProductFromRedListDialog = RemoveProductFromRedListDialog()
    }
}