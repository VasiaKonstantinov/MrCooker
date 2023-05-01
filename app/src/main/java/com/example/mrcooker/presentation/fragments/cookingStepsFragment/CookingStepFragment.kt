package com.example.mrcooker.presentation.fragments.cookingStepsFragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mrcooker.R
import com.example.mrcooker.data.utils.IS_LAST_STEP
import com.example.mrcooker.data.utils.parcelable
import com.example.mrcooker.data.utils.toEditable
import com.example.mrcooker.data.utils.RECIPE_STEP_KEY
import com.example.mrcooker.data.utils.dataClasses.CookingStep
import com.example.mrcooker.data.utils.dataClasses.RecipeStepAndIngredientsList
import com.example.mrcooker.databinding.CookingStepFragmentBinding
import com.example.mrcooker.presentation.dialogs.SetDishRateDialog
import com.example.mrcooker.presentation.fragments.chooseDishFragment.ChooseDishFragmentDirections
import com.example.mrcooker.presentation.fragments.chooseDishFragment.ChooseDishFragmentViewModel
import com.example.mrcooker.presentation.fragments.cookingViewPagerFragment.CookingViewPagerFragmentDirections
import kotlinx.coroutines.*

class CookingStepFragment : Fragment() {

    private lateinit var binding: CookingStepFragmentBinding
    private val viewModel by viewModels<ChooseDishFragmentViewModel>()
    private var recipeStepAndIngredientsList: RecipeStepAndIngredientsList? = null
    private var cookingStep: CookingStep? = null
    private var coroutineScope = CoroutineScope(Dispatchers.IO)
    private var timerJob: Job? = null
    private var sharedPref: SharedPreferences? = null
    private var timerIsStarted = false
    private var timerTime = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        cookingStep = requireArguments().parcelable(RECIPE_STEP_KEY)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CookingStepFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cookingStep?.cookingStepTime?.let { timerTime = it }
        sharedPref = activity?.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        if (cookingStep?.cookingStepTime != 0) {
            binding.btnStartTimer.visibility = View.VISIBLE
        }
        binding.btnStartTimer.setOnClickListener {
            if (cookingStep?.cookingStepTime != 0) {
                timerIsStarted = !timerIsStarted
                if (timerIsStarted) startTimer(timerTime)
                else stopTimer()
            }
        }
        binding.tvCookingStep.text = cookingStep?.cookingStepInfo
        binding.btnNextStep.setOnClickListener {
            if (cookingStep?.isLastStep!!) startChooseProductsFragment()
            else onContactChangedListener?.onNextPage()
        }

        if (cookingStep?.isLastStep!!) {
            binding.btnNextStep.text = "Finish"
            binding.btnSetRate.visibility = View.VISIBLE
            binding.btnSetRate.setOnClickListener {
                startSetRateDialog()
            }
        }
    }

    private fun startSetRateDialog() {
        val fragment = SetDishRateDialog.newInstance()

        fragment.onRateSetListener = object : SetDishRateDialog.OnRateSetListener {
            override fun onRateSet(rate: Float) {
                Toast.makeText(requireContext(), "Еhe rating has been updated", Toast.LENGTH_SHORT)
                    .show()
                sharedPref?.edit()?.putFloat(cookingStep?.rateKey, rate)?.commit()
                updateDish?.onDishUpdate()
            }
        }
        fragment.show(requireActivity().supportFragmentManager, "DialogEditUserNameFragment")
    }

    private fun startTimer(cookingStepTime: Int) {
        binding.etTimer.visibility = View.VISIBLE
        if (timerJob == null) {
            timerJob = coroutineScope.launch {
                for (i in cookingStepTime downTo 0) {
                    delay(1000)
                    this.launch(Dispatchers.Main) {
                        timerTime = i
                        binding.etTimer.text = i.toString().toEditable()
                    }
                }
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    private fun startChooseProductsFragment() {
        val action =
            CookingViewPagerFragmentDirections.actionCookingViewPagerFragmentToChooseProductsFragment()
        findNavController().navigate(action)
    }

    private fun startAllDishFragment() {
        val action = ChooseDishFragmentDirections.actionChooseDishFragmentToViewAllDishFragment()
        findNavController().navigate(action)
    }

    interface UpdateDish {
        fun onDishUpdate()
    }

    interface OnNextPageListener {
        fun onNextPage()
    }

    companion object {
        var onContactChangedListener: OnNextPageListener? = null
        var updateDish: UpdateDish? = null
    }
}