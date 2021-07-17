package com.example.bridal.ui.productdetails

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.RatingBar
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.bridal.R
import com.example.bridal.databinding.FragmentProductDetailsBinding
import com.example.bridal.model.ProductModel
import com.example.bridal.ui.glideLoader
import com.example.bridal.util.Constants


class ProductDetailsFragment : Fragment() {

    lateinit var binding : FragmentProductDetailsBinding
    private var mMyAddProduct : ProductModel? = null
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // show progress bar.
        binding.loadingView.visibility = View.GONE

        // show product details for product add by user.
        if(arguments?.containsKey(Constants.PRODUCT_ITEM_KEY) == true){
            // show details for product from my add page.
            mMyAddProduct = arguments?.getSerializable(Constants.PRODUCT_ITEM_KEY) as ProductModel
            binding.loadingView.visibility = View.VISIBLE
            binding.apply {
                tvProductName.text          = mMyAddProduct!!.productTitle
                tvProductPrice.text         = "$${mMyAddProduct!!.productPrice}"
                tvCategoryName.text         = mMyAddProduct!!.categoryName
                tvProductAdded.text         = mMyAddProduct!!.userName
                tvProductDescription.text   = mMyAddProduct!!.productDescription

                // hide rate bar.
                ratingBar.visibility        = View.GONE

                // hide chat button.
                ivGoToChat.visibility = View.GONE

                // operation work for carouseView.
                val image = arrayOf(mMyAddProduct!!.productImageOne,
                    mMyAddProduct!!.productImageTow,
                    mMyAddProduct!!.productImageThree,
                    mMyAddProduct!!.productImageFour,
                    mMyAddProduct!!.productImageFive,
                    mMyAddProduct!!.productImageSix)

                // operation work fo video.
                // video one.
                val mediaControllerOne = MediaController(requireActivity())
                mediaControllerOne.setAnchorView(viViewOne)
                viViewOne.setMediaController(mediaControllerOne)
                viViewOne.setVideoURI(mMyAddProduct!!.productVideo.toUri())
                viViewOne.requestFocus()


                //video two
                val mediaControllerTwo = MediaController(requireActivity())
                mediaControllerTwo.setAnchorView(viViewTwo)
                viViewTwo.setMediaController(mediaControllerTwo)
                viViewTwo.setVideoURI(mMyAddProduct!!.productVideoTwo.toUri())
                viViewTwo.requestFocus()

                // video three
                val mediaControllerThree = MediaController(requireActivity())
                mediaControllerThree.setAnchorView(viViewThree)
                viViewThree.setMediaController(mediaControllerThree)
                viViewThree.setVideoURI(mMyAddProduct!!.productVideoThree.toUri())
                viViewThree.requestFocus()

                val cities = arrayOf("1","2","3","4","5","6")
                carouseView.setImageListener { position, imageView ->
                    glideLoader(requireActivity()).loadUserPicture(image[position],imageView)
                    loadingView.visibility = View.GONE
                }
                carouseView.pageCount = cities.size
                carouseView.setImageClickListener { position ->
                    // go show full image.
                    val bundle = bundleOf(Constants.IMAGE_FROM_MY_ADD_DETAILS to image[position])
                    findNavController().navigate(R.id.action_productDetailsFragment_to_viewFullImageFragment , bundle)
                }
            }
        }

        // show product details for public.
        if(arguments?.containsKey(Constants.EXTRA_PRODUCT_ITEM_KEY) == true){
            // show details for product from home page
            mMyAddProduct = arguments?.getSerializable(Constants.EXTRA_PRODUCT_ITEM_KEY) as ProductModel
            binding.loadingView.visibility = View.VISIBLE
            binding.apply {
                tvProductName.text          = mMyAddProduct!!.productTitle
                tvProductPrice.text         = "$${mMyAddProduct!!.productPrice}"
                tvCategoryName.text         = mMyAddProduct!!.categoryName
                tvProductAdded.text         = mMyAddProduct!!.userName
                tvProductDescription.text   = mMyAddProduct!!.productDescription

                // operation work fo video.
                // video one.
                val mediaControllerOne = MediaController(requireActivity())
                mediaControllerOne.setAnchorView(viViewOne)
                viViewOne.setMediaController(mediaControllerOne)
                viViewOne.setVideoURI(mMyAddProduct!!.productVideo.toUri())
                viViewOne.requestFocus()


                //video two
                val mediaControllerTwo = MediaController(requireActivity())
                mediaControllerTwo.setAnchorView(viViewTwo)
                viViewTwo.setMediaController(mediaControllerTwo)
                viViewTwo.setVideoURI(mMyAddProduct!!.productVideoTwo.toUri())
                viViewTwo.requestFocus()

                // video three
                val mediaControllerThree = MediaController(requireActivity())
                mediaControllerThree.setAnchorView(viViewThree)
                viViewThree.setMediaController(mediaControllerThree)
                viViewThree.setVideoURI(mMyAddProduct!!.productVideoThree.toUri())
                viViewThree.requestFocus()

                // operation work for carouseView.
                val image = arrayOf(mMyAddProduct!!.productImageOne,
                    mMyAddProduct!!.productImageTow,
                    mMyAddProduct!!.productImageThree,
                    mMyAddProduct!!.productImageFour,
                    mMyAddProduct!!.productImageFive,
                    mMyAddProduct!!.productImageSix)
                val cities = arrayOf("1","2","3","4","5","6")
                carouseView.setImageListener { position, imageView ->
                    glideLoader(requireActivity()).loadUserPicture(image[position],imageView)
                    loadingView.visibility = View.GONE
                }
                carouseView.pageCount = cities.size
                carouseView.setImageClickListener { position ->
                    // go show full image.
                    val bundle = bundleOf(Constants.IMAGE_FROM_HOME_DETAILS to image[position])
                    findNavController().navigate(R.id.action_productDetailsFragment_to_viewFullImageFragment,bundle)
                }

                if(mMyAddProduct!!.userId == Constants.getCurrentUser()){
                    ivGoToChat.visibility = View.GONE
                }else{
                    // go to chat.
                    ivGoToChat.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putSerializable(Constants.PRODUCT_CHAT_OBJECT , mMyAddProduct)
                        findNavController().navigate(R.id.action_productDetailsFragment_to_chatFragment,bundle)
                    }
                }
            }
            // call fun rating product.
            ratingProduct()
            val sharedPreference = requireActivity().getSharedPreferences( Constants.RATING_KEY, Context.MODE_PRIVATE)
            val rating = sharedPreference.getFloat(Constants.RATING_KEY, 0f)
            binding.ratingBar.rating = rating
        }
    }

    // fun for operation rating product.
    private fun ratingProduct(){
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            val sharedPreference = requireActivity().getSharedPreferences(Constants.RATING_KEY, Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putFloat(Constants.RATING_KEY, rating)
            editor.apply()
        }
    }
}