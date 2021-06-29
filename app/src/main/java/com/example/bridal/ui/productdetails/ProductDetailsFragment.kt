package com.example.bridal.ui.productdetails

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

                // operation work for carouseView.
                val image = arrayOf(mMyAddProduct!!.productImageOne,
                    mMyAddProduct!!.productImageTow,
                    mMyAddProduct!!.ProductImageThree)
                val cities = arrayOf("1","2","3")
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

                // operation work for carouseView.
                val image = arrayOf(mMyAddProduct!!.productImageOne,
                    mMyAddProduct!!.productImageTow,
                    mMyAddProduct!!.ProductImageThree)
                val cities = arrayOf("1","2","3")
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
            }
        }
    }
}