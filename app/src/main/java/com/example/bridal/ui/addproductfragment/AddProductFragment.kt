package com.example.bridal.ui.addproductfragment

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.example.bridal.R
import com.example.bridal.databinding.FragmentAddProductBinding
import com.example.bridal.util.Constants

class AddProductFragment : Fragment() {

    lateinit var binding            : FragmentAddProductBinding
    private val addProductViewModel : AddProductViewModel by viewModels()
    lateinit var imageUriOne        : Uri
    lateinit var imageUriTow        : Uri
    lateinit var imageUriThree      : Uri
    lateinit var imageUriFour       : Uri
    lateinit var imageUriFive       : Uri
    lateinit var imageUriSix        : Uri
    lateinit var videoUri           : Uri
    lateinit var videoUriTow        : Uri
    lateinit var videoUriThree      : Uri

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddProductBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect with viewModel.
        binding.lifecycleOwner      = this
        binding.addProductFragment  = addProductViewModel

        // get user premium from shared preference.
        val sharedPreference = requireActivity().getSharedPreferences(Constants.USERS_SHARED_KEY, Context.MODE_PRIVATE)
        val userPremium = sharedPreference!!.getInt(Constants.USER_PREMIUM_COMPLETE,0)

        // default image and video in firebase.
        imageUriOne     = Constants.SOURCE_IMAGE_ONE.toUri()
        imageUriTow     = Constants.SOURCE_IMAGE_TOW.toUri()
        imageUriThree   = Constants.SOURCE_IMAGE_THREE.toUri()
        imageUriFour    = Constants.SOURCE_IMAGE_FOUR.toUri()
        imageUriFive    = Constants.SOURCE_IMAGE_FIVE.toUri()
        imageUriSix     = Constants.SOURCE_IMAGE_SIX.toUri()
        videoUri        = Constants.SOURCE_VIDEO.toUri()
        videoUriTow     = Constants.SOURCE_VIDEO.toUri()
        videoUriThree   = Constants.SOURCE_VIDEO.toUri()

        binding.loadingView.visibility = View.GONE

        // select image one.
        binding.ivImageOne.setOnClickListener {
            pickImageNumOne()
        }

        // select image tow.
        binding.ivImageTow.setOnClickListener {
            picImageNumTow()
        }

        // select image three.
        binding.ivImageThree.setOnClickListener {
            pickImageNumThree()
        }

        // select image four.
        binding.ivImagePremiumOne.setOnClickListener {
            pickImageFour()
        }

        // select image five.
        binding.ivImagePremiumTow.setOnClickListener {
            pickImageFive()
        }

        // select image six.
        binding.ivImagePremiumThree.setOnClickListener {
            pickImageSix()
        }

        // select video.
        binding.btnUploadVideo.setOnClickListener {
            pickVideo()
        }

        // select video two.
        binding.btnUploadVideoTow.setOnClickListener {
            if( userPremium == 0 ){
                Toast.makeText(requireActivity(),"Please go to premium",Toast.LENGTH_SHORT).show()
            }else{
                pickVideoTwo()
            }
        }

        // select video three.
        binding.btnUploadVideoThree.setOnClickListener {
            if( userPremium == 0 ){
                Toast.makeText(requireActivity(),"Please go to premium",Toast.LENGTH_SHORT).show()
            }else{
                pickVideoThree()
            }
        }

        binding.ivImagePremiumOneRequire.setOnClickListener {
            Toast.makeText(requireActivity(),"Please go to premium",Toast.LENGTH_SHORT).show()
        }

        binding.ivImagePremiumTowRequire.setOnClickListener {
            Toast.makeText(requireActivity(),"Please go to premium",Toast.LENGTH_SHORT).show()
        }

        binding.ivImagePremiumThreeRequire.setOnClickListener {
            Toast.makeText(requireActivity(),"Please go to premium",Toast.LENGTH_SHORT).show()
        }

        // call fun show select more image. when go user update profile to premium option will go unlock select more image.
        addProductViewModel.addShowMoreImageAndVideo(requireActivity(),binding.llUserPremiumRequire,binding.llUserPremiumDone)

        // select category.
        binding.spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected( adapterView : AdapterView<*>?, viewItem: View?, position : Int, id: Long ) {

                //Toast.makeText(requireActivity(),"${adapterView?.getItemAtPosition(position)}",Toast.LENGTH_SHORT).show()
                ///Toast.makeText(requireActivity(),"${position-1}",Toast.LENGTH_SHORT).show()

                val categoryName = adapterView?.getItemAtPosition(position).toString()
                binding.btnSaveProduct.setOnClickListener {
                    if(position-1 == -1){
                        Constants.showErrorSnackBar(resources.getString((R.string.err_validate_select_category)), true , requireActivity() , view)
                    }else{
                        addProductViewModel.addProductItem(requireActivity(),
                            view,
                            "${position-1}",
                            categoryName,
                            imageUriOne,
                            imageUriTow,
                            imageUriThree,
                            imageUriFour,
                            imageUriFive,
                            imageUriSix,
                            videoUri,
                            videoUriTow,
                            videoUriThree,
                            binding.loadingView)
                    }
                }
                binding.loadingView.visibility = View.GONE
            }
            override fun onNothingSelected(adapterView : AdapterView<*>?) {
                
            }
        }
    }

    // fun select image number one.
    private fun pickImageNumOne(){
        if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,Constants.IMAGE_NUM_ONE_KEY)
        }
    }

    // fun select image number tow.
    private fun picImageNumTow(){
        if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,Constants.IMAGE_NUM_TOW_KEY)
        }
    }

    // fun select image number three.
    private fun pickImageNumThree(){
        if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,Constants.IMAGE_NUM_THREE_KEY)
        }
    }

    // fun select image number four.
    private fun pickImageFour(){
        if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent , Constants.IMAGE_NUM_FOUR_KEY)
        }
    }
    // fun select image number five.
    private fun pickImageFive(){
        if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,Constants.IMAGE_NUM_FIVE_KEY)
        }
    }
    // fun select image number six.
    private fun pickImageSix(){
        if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,Constants.IMAGE_NUM_SIX_KEY)
        }
    }
    // fun select video.
    private fun pickVideo(){
        if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "video/*"
            startActivityForResult(intent,Constants.VIDEO_KEY)
        }
    }

    // fun select video.
    private fun pickVideoTwo(){
        if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "video/*"
            startActivityForResult(intent,Constants.VIDEO_TWO_KEY)
        }
    }

    // fun select video.
    private fun pickVideoThree(){
        if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "video/*"
            startActivityForResult(intent,Constants.VIDEO_THREE_KEY)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Constants.IMAGE_NUM_ONE_KEY && resultCode == AppCompatActivity.RESULT_OK){

            imageUriOne = data?.data!!
            binding.ivImageOne.setImageURI(imageUriOne)
        }
        if(requestCode == Constants.IMAGE_NUM_TOW_KEY && resultCode == AppCompatActivity.RESULT_OK){
            imageUriTow = data?.data!!
            binding.ivImageTow.setImageURI(imageUriTow)
        }
        if(requestCode == Constants.IMAGE_NUM_THREE_KEY && resultCode == AppCompatActivity.RESULT_OK){
            imageUriThree = data?.data!!
            binding.ivImageThree.setImageURI(imageUriThree)
        }
        if (requestCode == Constants.IMAGE_NUM_FOUR_KEY && resultCode == AppCompatActivity.RESULT_OK){
            imageUriFour = data?.data!!
            binding.ivImagePremiumOne.setImageURI(imageUriFour)
        }
        if( requestCode == Constants.IMAGE_NUM_FIVE_KEY && resultCode == AppCompatActivity.RESULT_OK){
            imageUriFive = data?.data!!
            binding.ivImagePremiumTow.setImageURI(imageUriFive)
        }
        if( requestCode == Constants.IMAGE_NUM_SIX_KEY && resultCode == AppCompatActivity.RESULT_OK){
            imageUriSix = data?.data!!
            binding.ivImagePremiumThree.setImageURI(imageUriSix)
        }
        if( requestCode == Constants.VIDEO_KEY && resultCode == AppCompatActivity.RESULT_OK){
            videoUri = data?.data!!
        }
        if( requestCode == Constants.VIDEO_TWO_KEY && resultCode == AppCompatActivity.RESULT_OK){
            videoUriTow = data?.data!!
        }
        if( requestCode == Constants.VIDEO_THREE_KEY && resultCode == AppCompatActivity.RESULT_OK){
            videoUriThree = data?.data!!
        }
    }
}