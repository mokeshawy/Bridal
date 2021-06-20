package com.example.bridal.ui.addproductfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.bridal.R
import com.example.bridal.databinding.FragmentAddProductBinding

class AddProductFragment : Fragment() {

    lateinit var binding : FragmentAddProductBinding
    private val addProductViewModel : AddProductViewModel by viewModels()
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddProductBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect with viewModel.
        binding.lifecycleOwner = this
        binding.addProductFragment = addProductViewModel


        binding.spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected( adapterView : AdapterView<*>?, view: View?, position: Int, id: Long ) {
                val categoryName = adapterView?.getItemAtPosition(position).toString()
                binding.btnSaveProduct.setOnClickListener {
                    addProductViewModel.addProductItem(categoryName)
                }
            }
            override fun onNothingSelected(adapterView : AdapterView<*>?) {

            }
        }
    }
}