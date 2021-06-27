package com.example.bridal.ui.settingsaccountfragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.bridal.R
import com.example.bridal.databinding.FragmentSettingsAccountBinding
import com.example.bridal.model.UserModel
import com.example.bridal.ui.glideLoader
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

        // call function for show data for user.
        settingsAccountViewModel.showDataForUser(requireActivity(),binding.ivUserPhoto)

        // call function for observer.
        observer()

        // show text for allow notification.
        binding.tvNotification.text = resources.getString(R.string.allow)

        binding.tvEdit.setOnClickListener {
            findNavController().navigate(R.id.action_settingsAccountFragment_to_completeProfileFragment)
        }

        // call fun loadLocated change language.
        settingsAccountViewModel.loadLocated(requireActivity())
        // radio button for change language.
        binding.rbChangeLanguage.setOnCheckedChangeListener { group, checkedId ->
           binding.btnChangeLanguage.setOnClickListener {
               if(checkedId == R.id.radio_english){
                   settingsAccountViewModel.setLocate(requireActivity(),"en")
                   activity?.recreate()
               }
               if(checkedId == R.id.radio_arabic){
                   settingsAccountViewModel.setLocate(requireActivity(),"ar")
                   activity?.recreate()
               }
           }
        }
    }

    // fun for observer.
    private fun observer(){
        settingsAccountViewModel.tvName.observe(viewLifecycleOwner, Observer {
            binding.tvName.text = it
        })

        settingsAccountViewModel.tvEmail.observe(viewLifecycleOwner, Observer {
            binding.tvEmail.text = it
        })

        settingsAccountViewModel.tvPhoneNumber.observe(viewLifecycleOwner, Observer {
            binding.tvPhoneNumber.text = it
        })
    }
}