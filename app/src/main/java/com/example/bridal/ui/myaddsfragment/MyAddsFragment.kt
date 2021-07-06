package com.example.bridal.ui.myaddsfragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.bridal.R
import com.example.bridal.adapter.ProductForUserAdapter
import com.example.bridal.databinding.FragmentMyAddsBinding
import com.example.bridal.interfaceforclickadapter.OnClickProductForUserAdapter
import com.example.bridal.model.ProductModel
import com.example.bridal.ui.activits.NotificationActivity
import com.example.bridal.util.Constants

class MyAddsFragment : Fragment() , OnClickProductForUserAdapter{

    lateinit var binding : FragmentMyAddsBinding
    private val myAddsViewModel : MyAddsViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = FragmentMyAddsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect with viewModel.
        binding.lifecycleOwner = this
        binding.myAddFragment = myAddsViewModel

        // for show menu in action bar. (for fragment)
        setHasOptionsMenu(true)


        // show item added by user.
        myAddsViewModel.showProductForUser(requireActivity() , binding.loadingView , binding.tvUserProduct)
        myAddsViewModel.userProductLiveData.observe(viewLifecycleOwner, Observer {
            binding.rvUserProduct.adapter = ProductForUserAdapter(it,this)
        })

        // go notification activity.
        binding.btnFlotNotification.setOnClickListener {
            startActivity(Intent(activity,NotificationActivity::class.java))
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.addproductmenu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.addpro -> {
                findNavController().navigate(R.id.action_myAddsFragment_to_addProductFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // implement interface for click item product.
    override fun onClickProductForUser(
        viewHolder: ProductForUserAdapter.ViewHolder,
        productModel: ProductModel,
        position: Int
    ) {
        // go product to details.
        viewHolder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(Constants.PRODUCT_ITEM_KEY,productModel)
            findNavController().navigate(R.id.action_myAddsFragment_to_productDetailsFragment,bundle)
        }

        // delete Product.
        viewHolder.binding.ibDeleteProduct.setOnClickListener {
            myAddsViewModel.deleteProduct(requireActivity(),productModel.pushKey)
        }
    }
}
