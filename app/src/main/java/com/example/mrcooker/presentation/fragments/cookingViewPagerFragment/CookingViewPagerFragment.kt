package com.example.mrcooker.presentation.fragments.cookingViewPagerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.mrcooker.data.utils.dataClasses.RecipeStepAndIngredientsList
import com.example.mrcooker.databinding.CookingViewPagerFragmentBinding
import com.example.mrcooker.presentation.fragments.cookingStepsFragment.CookingStepFragment
import com.example.mrcooker.presentation.fragments.cookingStepsFragment.CookingStepsFragmentsViewModel
import com.example.mrcooker.presentation.recyclerViews.viewPagerAdapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CookingViewPagerFragment @Inject constructor() : Fragment() {

    private lateinit var binding: CookingViewPagerFragmentBinding
    private val viewModel by viewModels<CookingStepsFragmentsViewModel>()
    private val args by navArgs<CookingViewPagerFragmentArgs>()
    private var recipeStepAndIngredientsList: RecipeStepAndIngredientsList? = null
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipeStepAndIngredientsList = args.recipeStepAndIngredientsList
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CookingViewPagerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setNextPageListener()
    }

    private fun setupViewPager() {
        recipeStepAndIngredientsList?.let {
            viewPagerAdapter = ViewPagerAdapter(this, it)
            binding.pager.adapter = viewPagerAdapter
        }
    }

    private fun setNextPageListener() {
        CookingStepFragment.onContactChangedListener =
            object : CookingStepFragment.OnNextPageListener {
                override fun onNextPage() {
                    binding.pager.currentItem = binding.pager.currentItem + 1
                }
            }
    }
}