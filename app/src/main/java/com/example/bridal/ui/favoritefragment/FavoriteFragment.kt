package com.example.bridal.ui.favoritefragment

import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.bridal.adapter.FavoriteAdapter
import com.example.bridal.databinding.FragmentFavoriteBinding
import com.example.bridal.interfaceforclickadapter.OnClickFavoriteAdapter
import com.example.bridal.model.ProductModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        favoriteViewModel.checkSelect(requireActivity(),productModel.pushKey , viewHolder.binding.btnFavoriteProduct)
        val checkBoxArray = SparseBooleanArray()
        viewHolder.binding.btnFavoriteProduct.isChecked = checkBoxArray.get( position , false)

        // add favorite and un favorite product.
        viewHolder.binding.btnFavoriteProduct.setOnClickListener {
            if(!checkBoxArray.get( position , false)){
                viewHolder.binding.btnFavoriteProduct.isChecked = true
                checkBoxArray.put(position , true)
                // call function for add job from database to favorite
                favoriteViewModel.addProductToFavorite(requireActivity(),productModel)
            }else{
                viewHolder.binding.btnFavoriteProduct.isChecked = false
                checkBoxArray.put(position , false)
                // call function unFavorite from database.
                favoriteViewModel.unFavoriteProduct(requireActivity(),productModel.pushKey)
            }
        }
    }
}