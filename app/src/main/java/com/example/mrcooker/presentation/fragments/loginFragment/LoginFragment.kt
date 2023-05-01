package com.example.mrcooker.presentation.fragments.loginFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mrcooker.data.room.UserData
import com.example.mrcooker.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            loginButton.setOnClickListener {
                if (viewModel.tryToLogin(
                        UserData(
                            userName = etUserName.text.toString(),
                            password = etPassword.text.toString()
                        )
                    )
                ) {
                    startChooseProductFragment()
                } else {
                    Toast.makeText(requireContext(), "User not fount", Toast.LENGTH_SHORT).show()
                }
            }
            registerButton.setOnClickListener {
                startRegistrationFragment()
            }
        }
    }

    private fun startChooseProductFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToChooseProductsFragment()
        findNavController().navigate(action)
    }

    private fun startRegistrationFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
        findNavController().navigate(action)
    }
}