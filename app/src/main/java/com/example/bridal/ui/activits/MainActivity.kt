package com.example.bridal.ui.activits

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bridal.R
import com.example.bridal.databinding.ActivityMainBinding
import com.example.bridal.util.Constants

class MainActivity : AppCompatActivity(){

    lateinit var binding : ActivityMainBinding
    lateinit var navHostFragment    : NavHostFragment
    lateinit var navController      : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        // operation work for fragment from navigation component.
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController   = navHostFragment.navController
        // setUp navigation bottom.
        findViewById<BottomNavigationView>(R.id.navigation_bottom).setupWithNavController(navController)

        // show title for fragment on actionbar.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment,
            R.id.dashBoardFragment,
            R.id.myAddsFragment,
            R.id.myAccountFragment,
            R.id.loginFragment,
            R.id.registerFragment,
            R.id.forgetPasswordFragment,
            R.id.addProductFragment,
            R.id.productFragment,
            R.id.completeProfileFragment,
            R.id.settingsAccountFragment,
            R.id.productDetailsFragment))
       setupActionBarWithNavController(navController, appBarConfiguration)


        // Operation work for hide and show actionbar on fragment.
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.splashFragment             -> supportActionBar!!.hide()
                R.id.viewFullImageFragment      -> supportActionBar!!.hide()
                else -> supportActionBar!!.show()
            }
        }

        // show and hide navigation bottom on fragment page.
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.homeFragment       -> binding.navigationBottom.visibility = View.VISIBLE
                R.id.dashBoardFragment  -> binding.navigationBottom.visibility = View.VISIBLE
                R.id.myAddsFragment     -> binding.navigationBottom.visibility = View.VISIBLE
                R.id.myAccountFragment  -> binding.navigationBottom.visibility = View.VISIBLE

                else -> binding.navigationBottom.visibility = View.GONE
            }
        }
        val sharedPreferences = getSharedPreferences(Constants.bridalpreference, Context.MODE_PRIVATE)
        val username = sharedPreferences.getString(Constants.logged_username," ")!!
    }
}