package com.example.bridal.ui.favoritefragment

import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.bridal.R
import com.example.bridal.adapter.FavoriteAdapter
import com.example.bridal.databinding.FragmentFavoriteBinding
import com.example.bridal.interfaceforclickadapter.OnClickFavoriteAdapter
import com.example.bridal.model.ProductModel
import com.example.bridal.util.Constants


class FavoriteFragment : Fragment() , OnClickFavoriteAdapter{


    lateinit var binding : FragmentFavoriteBinding
    private val favoriteViewModel : FavoriteViewModel by viewModels()
    var favoriteAdapter = FavoriteAdapter(arrayListOf(),this)
    override fun onCreateView( inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect with viewModel
        binding.lifecycleOwner = this
        binding.favoriteFragment = favoriteViewModel


        // show data form database.
        binding.rvFavoriteList.adapter = favoriteAdapter
        favoriteViewModel.selectFavorite(requireActivity()).observe(viewLifecycleOwner, Observer {
            binding.loadingView.visibility  = View.VISIBLE

            favoriteAdapter.update(it)

            if(it.isNotEmpty()){
                binding.rvFavoriteList.visibility       = View.VISIBLE
                binding.tvFavoriteNotFound.visibility   = View.GONE
            }else{
                binding.rvFavoriteList.visibility       = View.GONE
                binding.tvFavoriteNotFound.visibility   = View.VISIBLE
            }
            binding.loadingView.visibility  = View.GONE
        })

    }

    override fun onClickFavorite(
        viewHolder: FavoriteAdapter.ViewHolder,
        productModel: ProductModel,
        position: Int ) {

        // check select for item.
        favoriteViewModel.checkSelect(requireActivity(),productModel.pushKey , viewHolder.binding.btnFavoriteProduct)

        // add favorite and un favorite product.
        viewHolder.binding.btnFavoriteProduct.setOnClickListener {
            favoriteViewModel.unFavoriteProduct(requireActivity(),productModel.pushKey)
            favoriteViewModel.selectFavorite(requireActivity()).observe(viewLifecycleOwner, Observer {
                binding.loadingView.visibility  = View.VISIBLE
                favoriteAdapter.update(it)

                if(it.isNotEmpty()){
                    binding.rvFavoriteList.visibility       = View.VISIBLE
                    binding.tvFavoriteNotFound.visibility   = View.GONE
                }else{
                    binding.rvFavoriteList.visibility       = View.GONE
                    binding.tvFavoriteNotFound.visibility   = View.VISIBLE
                }
                binding.loadingView.visibility  = View.GONE
            })
        }

        // go details fragment.
        viewHolder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(Constants.PRODUCT_ITEM_KEY,productModel)
            findNavController().navigate(R.id.action_favoriteFragment_to_productDetailsFragment,bundle)
        }
    }
}