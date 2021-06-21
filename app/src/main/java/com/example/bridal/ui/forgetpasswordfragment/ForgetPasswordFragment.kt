package com.example.bridal.ui.forgetpasswordfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.bridal.R
import com.example.bridal.databinding.FragmentForgetPasswordBinding

class ForgetPasswordFragment : Fragment() {

    lateinit var binding : FragmentForgetPasswordBinding
    private val forgetPasswordViewModel : ForgetPasswordViewModel by viewModels()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgetPasswordBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect with viewModel
        binding.lifecycleOwner = this
        binding.forgetPasswordFragment = forgetPasswordViewModel


        // hide progress bar.
        binding.loadingView.visibility = View.GONE

        // btn submit for send email change password
        binding.btnSubmit.setOnClickListener {
            forgetPasswordViewModel.forgetPassword(requireActivity(),view , binding.loadingView)
        }
    }
}