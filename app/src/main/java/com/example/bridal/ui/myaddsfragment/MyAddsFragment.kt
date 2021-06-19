package com.example.bridal.ui.myaddsfragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.bridal.R
import com.example.bridal.databinding.FragmentMyAddsBinding

class MyAddsFragment : Fragment() {

    lateinit var binding : FragmentMyAddsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = FragmentMyAddsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.textNotifications.text = "My Adds"

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.addproductmenu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when(id) {
            R.id.addpro -> {

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
