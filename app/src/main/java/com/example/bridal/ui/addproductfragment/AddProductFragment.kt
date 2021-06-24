package com.example.bridal.ui.addproductfragment

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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
    lateinit var videoUri           : Uri

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

        // default image in firebase.
        imageUriOne     = Constants.SOURCE_IMAGE_ONE.toUri()
        imageUriTow     = Constants.SOURCE_IMAGE_TOW.toUri()
        imageUriThree   = Constants.SOURCE_IMAGE_THREE.toUri()
        videoUri = Constants.SOURCE_IMAGE_THREE.toUri()

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

        // select video.
        binding.btnUploadVideo.setOnClickListener {
            pickVideo()
        }

        addProductViewModel.addShowMoreImage(requireActivity(),binding.llUserPremiumRequire,binding.llUserPremiumDone)

        // select category.
        binding.spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected( adapterView : AdapterView<*>?, viewItem: View?, position: Int, id: Long ) {

                //Toast.makeText(requireActivity(),adapterView?.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show()
                val categoryName = adapterView?.getItemAtPosition(position).toString()
                binding.btnSaveProduct.setOnClickListener {
                    if(position == 0){
                        Constants.showErrorSnackBar(resources.getString((R.string.err_validate_select_category)), true , requireActivity() , view)
                    }else{
                        addProductViewModel.addProductItem(requireActivity(),
                            view,
                            categoryName,
                            imageUriOne,
                            imageUriTow,
                            imageUriThree,
                            videoUri,
                            binding.loadingView)
                    }
                }
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
        if( requestCode == Constants.VIDEO_KEY && resultCode == AppCompatActivity.RESULT_OK){
            videoUri = data?.data!!
        }
    }
}