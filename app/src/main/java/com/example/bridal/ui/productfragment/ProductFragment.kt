package com.example.bridal.ui.productfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.bridal.R
import com.example.bridal.adapter.ProductAdapter
import com.example.bridal.databinding.FragmentProductBinding
import com.example.bridal.util.Constants

class ProductFragment : Fragment() {

    lateinit var binding : FragmentProductBinding
    private val productViewModel : ProductViewModel by viewModels()
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect with viewModel
        binding.lifecycleOwner  = this
        binding.productFragment = productViewModel

        val categoryName = arguments?.getString(Constants.PRODUCT_ITEM_KEY)

        productViewModel.readProduct(categoryName.toString())
        productViewModel.productListLiveData.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter = ProductAdapter(it)
        })

    }
}