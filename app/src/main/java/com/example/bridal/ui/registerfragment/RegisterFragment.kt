package com.example.bridal.ui.registerfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bridal.R
import com.example.bridal.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    lateinit var binding : FragmentRegisterBinding
    private val registerViewModel : RegisterViewModel by viewModels()
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect with viewModel
        binding.lifecycleOwner      = this
        binding.registerFragment    = registerViewModel

        // hide progress bar.
        binding.loadingView.visibility = View.GONE

        // btn submit for register new user.
        binding.btnSubmit.setOnClickListener {
            registerViewModel.registerUser(requireActivity(),view,binding.loadingView)
        }

        // btn back to login page.
        binding.tvGoLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}