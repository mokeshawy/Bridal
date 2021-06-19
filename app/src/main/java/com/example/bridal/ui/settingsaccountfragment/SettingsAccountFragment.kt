package com.example.bridal.ui.settingsaccountfragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.bridal.R
import com.example.bridal.databinding.FragmentSettingsAccountBinding
import com.example.bridal.model.UserModel
import com.example.bridal.util.Constants

class SettingsAccountFragment : Fragment() {

    lateinit var binding : FragmentSettingsAccountBinding
    private val settingsAccountViewModel : SettingsAccountViewModel by viewModels()
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsAccountBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect with viewModel.
        binding.lifecycleOwner = this
        binding.settingsAccountFragment = settingsAccountViewModel

        val myPreference = activity?.getSharedPreferences(Constants.USERS_SHARED_KEY,Context.MODE_PRIVATE)

        binding.tvName.text = myPreference!!.getString(Constants.FIRST_NAME_KEY,"")
    }
}