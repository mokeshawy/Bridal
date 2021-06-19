package com.example.bridal.ui.loginfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.bridal.R
import com.example.bridal.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding        : FragmentLoginBinding
    private val loginViewModel  : LoginViewModel by viewModels()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //connect with viewModel
        binding.lifecycleOwner  = this
        binding.loginFragment   = loginViewModel

        loginViewModel.loadLocate(requireActivity())

        // btn change language.
        binding.btnChangeLanguage.setOnClickListener {
            //call fun change language.
            loginViewModel.showChangeLang(requireActivity(),this)
        }
    }
}