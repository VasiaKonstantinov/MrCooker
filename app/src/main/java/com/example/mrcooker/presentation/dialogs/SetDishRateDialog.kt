package com.example.mrcooker.presentation.dialogs

import com.example.mrcooker.R
import com.example.mrcooker.data.utils.viewBinding
import com.example.mrcooker.databinding.SetDishRateDialogBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class SetDishRateDialog : DialogFragment(R.layout.set_dish_rate_dialog) {

    private val binding by viewBinding(SetDishRateDialogBinding::bind)
    var onRateSetListener: OnRateSetListener? = null
    private var rate: Float = 0f

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
        binding.rateBar.setOnRatingBarChangeListener { _, rating, _ ->
            rate = rating
        }
        binding.btnSetRate.setOnClickListener {
            onRateSetListener?.onRateSet(rate)
            dismiss()
        }
    }

    interface OnRateSetListener {
        fun onRateSet(rate: Float)
    }

    companion object {
        fun newInstance(): SetDishRateDialog = SetDishRateDialog()
    }
}