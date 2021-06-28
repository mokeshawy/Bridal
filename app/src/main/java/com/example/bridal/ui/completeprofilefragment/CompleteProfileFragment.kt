package com.example.bridal.ui.completeprofilefragment

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.example.bridal.R
import com.example.bridal.databinding.FragmentCompleteProfileBinding
import com.example.bridal.ui.glideLoader
import com.example.bridal.util.Constants

class CompleteProfileFragment : Fragment() {

    lateinit var binding : FragmentCompleteProfileBinding
    private val completeProfileViewModel : CompleteProfileViewModel by viewModels()
    lateinit var profileUri : Uri
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCompleteProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect whit viewModel
        binding.lifecycleOwner = this
        binding.completeProfileFragment = completeProfileViewModel

        //shared preference for user details.
        val myPreference    = activity?.getSharedPreferences(Constants.USERS_SHARED_KEY,Context.MODE_PRIVATE)
        // get user complete profile key fro, shared preference.
        val completeProfile = myPreference!!.getInt(Constants.USER_COMPLETE_PROFILE,0)

        // source for default image profile
        profileUri = Constants.SOURCE_IMAGE_PROFILE.toUri()

        binding.loadingView.visibility = View.GONE

        // make change email notAllowed
        binding.etEmailId.isEnabled = false
        // call function for show user details in input.
        completeProfileViewModel.showDefaultData(requireActivity())


        //btn save details for complete profile and edit profile.
        binding.btnSubmitId.setOnClickListener {
            completeProfileViewModel.completeAndEditProfile(requireActivity(),
                view,
                binding.loadingView,
                profileUri,binding.rbMaleId)
        }

        // select image.
        binding.ivUserPhotoId.setOnClickListener {
            pickProfileImage()
        }




        // when user not complete profile will go complete profile page
        if(completeProfile!! == 0){
            (requireActivity() as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.title_complete_profile)
            binding.etFirstNameId.isEnabled = false
            binding.etLastNameId.isEnabled  = false

        }else{ // when user complete profile will go edit profile page.
            (requireActivity() as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.title_edit_profile)
            glideLoader(requireActivity()).loadUserPicture(myPreference!!.getString(Constants.USER_PROFILE_IMAGE,"").toString(),binding.ivUserPhotoId)
            binding.etFirstNameId.isEnabled = true
            binding.etLastNameId.isEnabled  = true
            if(myPreference!!.getString(Constants.USER_MOBILE_KEY,"") !=null){
                completeProfileViewModel.etMobileNumber.value = myPreference!!.getString(Constants.USER_MOBILE_KEY,"")
            }
            if(myPreference!!.getString(Constants.USER_GENDER_KEY,"") == Constants.GENDER_MALE){
                binding.rbMaleId.isChecked = true
            }else{
                binding.rbFemaleId.isChecked = false
            }
        }
    }

    // pick photo for profile and edit profile photo
    private fun pickProfileImage(){
        if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,Constants.IMAGE_PROFILE_KEY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Constants.IMAGE_PROFILE_KEY && resultCode == AppCompatActivity.RESULT_OK){
            profileUri = data?.data!!
            binding.ivUserPhotoId.setImageURI(profileUri)
        }
    }
}


