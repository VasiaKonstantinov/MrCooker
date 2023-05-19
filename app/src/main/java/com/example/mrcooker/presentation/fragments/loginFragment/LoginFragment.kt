package com.example.mrcooker.presentation.fragments.loginFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mrcooker.databinding.LoginFragmentBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var firebaseAuth: FirebaseAuth

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
        val firebaseApp = FirebaseApp.initializeApp(requireContext())
        firebaseApp?.let {
            firebaseAuth = FirebaseAuth.getInstance(it)
        }
        with(binding) {
            loginButton.setOnClickListener {
//                if (viewModel.tryToLogin(
//                        UserData(
//                            userName = etUserName.text.toString(),
//                            password = etPassword.text.toString()
//                        )
//                    )
//                ) {
//                    startChooseProductFragment()
//                } else {
//                    Toast.makeText(requireContext(), "User not fount", Toast.LENGTH_SHORT).show()
//                }
                tryAuth(etUserName.text.toString().trim(), etPassword.text.toString().trim())
            }
            registerButton.setOnClickListener {
                startRegistrationFragment()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        firebaseAuth.currentUser
        Log.d("ffgdfggdfgdf", currentUser.toString())
        if (currentUser != null) {
          //  createToast("currentUser == null")
            //  firebaseAuth.reload()
        }
    }

    private fun tryAuth(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    createToast("Success")
                    startChooseProductFragment()
                } else {
                    createToast("Something went wrong")
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

    private fun createToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}