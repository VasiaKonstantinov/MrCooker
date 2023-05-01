package com.example.mrcooker.presentation.mainActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.GravityCompat
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import com.example.mrcooker.R
import com.example.mrcooker.data.utils.viewBinding
import com.example.mrcooker.databinding.ActivityMainBinding
import com.example.mrcooker.presentation.fragments.redProductListFragment.RedProductsFragment
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.lang.System.exit

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_launcher_background)
//        binding.navigationView.itemIconTintList
//        binding.navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        Log.d("fdfdg", item.toString())
        var result = false
        when (id) {
            R.id.redList -> {
                result = true
                startRedListFragment()
            }
            R.id.dishList -> {
                result = true
                startDishFragment()
            }
            R.id.createDish -> {
                result = true
                startCreateDishList()
            }
        }
        return result
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val drawer = binding.drawerLayout

        if (item.itemId == android.R.id.home) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_launcher_background)
                drawer.closeDrawer(GravityCompat.START, true)
            } else {
                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_launcher_background)
                drawer.openDrawer(GravityCompat.START, true)
            }
        }
        return true
    }

    private fun startRedListFragment() {
//        val request = NavDeepLinkRequest.Builder
//            .fromUri("test".toUri())
//            .build()
        findNavController(R.id.nav_host_fragment).navigate(R.id.loginFragment)
        supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, RedProductsFragment())
            .commit()
    }

    private fun startDishFragment() {

    }

    private fun startCreateDishList() {

    }
}