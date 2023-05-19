package com.example.mrcooker.presentation.fragments.regestrationFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mrcooker.data.test
import com.example.mrcooker.databinding.LoginFragmentBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private val viewModel by viewModels<RegistrationViewModel>()
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
        binding.registerButton.visibility = View.GONE
        binding.loginButton.text = "Register user"
        binding.loginButton.setOnClickListener {
//            if (binding.etPassword.text.toString().isEmpty() || binding.etUserName.text.toString()
//                    .isEmpty()
//            ) {
//                createToast("User name or password is empty")
//            } else {
//                trySingIn(binding.etUserName.text.toString(), binding.etPassword.text.toString())
//                createToast("User successfully created")
//                viewModel.insertUserData(
//                    binding.etPassword.text.toString(),
//                    binding.etUserName.text.toString()
//                )
//             //   startChooseProductFragment()
//            }
            trySingIn(binding.etUserName.text.toString(), binding.etPassword.text.toString())
        }
    }

    private fun trySingIn(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    createToast("Successful")
                    startChooseProductFragment()
                } else {
                    createToast("Something went wrong")
                }
            }
    }

    private fun createToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun startChooseProductFragment() {
        val action =
            RegistrationFragmentDirections.actionRegistrationFragmentToChooseProductsFragment()
        findNavController().navigate(action)
    }
}