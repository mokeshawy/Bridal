package com.example.bridal.ui.productfragment

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
import com.example.bridal.adapter.ProductAdapter
import com.example.bridal.databinding.FragmentProductBinding
import com.example.bridal.interfaceforclickadapter.OnClickProductAdapter
import com.example.bridal.model.ProductModel
import com.example.bridal.util.Constants

class ProductFragment : Fragment() , OnClickProductAdapter{

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

        // get category name using bundle from home fragment.
        val categoryName = arguments?.getString(Constants.PRODUCT_ITEM_KEY)

        // call read product function.
        productViewModel.readProduct(categoryName.toString(),binding.tvProductNotFound,binding.loadingView)
        productViewModel.productListLiveData.observe(viewLifecycleOwner, Observer {
            binding.loadingView.visibility = View.VISIBLE
            binding.recyclerView.adapter = ProductAdapter(it,this)
            binding.loadingView.visibility = View.GONE

        })

    }

    override fun onClickProduct( viewHolder: ProductAdapter.ViewHolder,
                                 product: ProductModel,
                                 position: Int ) {

        viewHolder.itemView.setOnClickListener {
            var bundle = Bundle()
            bundle.putSerializable(Constants.EXTRA_PRODUCT_ITEM_KEY,product)
            findNavController().navigate(R.id.action_productFragment_to_productDetailsFragment,bundle)
        }
    }
}