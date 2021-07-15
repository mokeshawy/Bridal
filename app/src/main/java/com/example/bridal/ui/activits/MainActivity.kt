package com.example.bridal.ui.activits


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.bridal.R
import com.example.bridal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    lateinit var binding                : ActivityMainBinding
    lateinit var navHostFragment        : NavHostFragment
    lateinit var navController          : NavController
    lateinit var appBarConfiguration    : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        // operation work for fragment from navigation component.
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController   = navHostFragment.navController

        // setUp navigation bottom.
        binding.navigationBottom.setupWithNavController(navController)

        // show title for fragment on actionbar.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment,
            R.id.favoriteFragment,
            R.id.myAddsFragment,
            R.id.messageFragment,
            R.id.loginFragment,
            R.id.registerFragment,
            R.id.forgetPasswordFragment,
            R.id.addProductFragment,
            R.id.productFragment,
            R.id.completeProfileFragment,
            R.id.settingsAccountFragment,
            R.id.productDetailsFragment,
            R.id.viewPagerFragment))

        //binding.navigationView.setupWithNavController(navController)
       setupActionBarWithNavController( navController, appBarConfiguration )


        // Operation work for hide and show actionbar on fragment.
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.splashFragment             -> supportActionBar!!.hide()
                R.id.viewFullImageFragment      -> supportActionBar!!.hide()
                R.id.chatFragment               ->supportActionBar!!.hide()
                R.id.viewPagerFragment          ->supportActionBar!!.hide()
                else -> supportActionBar!!.show()
            }
        }

        // show and hide navigation bottom on fragment page.
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.homeFragment       -> binding.navigationBottom.visibility = View.VISIBLE
                R.id.favoriteFragment   -> binding.navigationBottom.visibility = View.VISIBLE
                R.id.myAddsFragment     -> binding.navigationBottom.visibility = View.VISIBLE
                R.id.messageFragment    -> binding.navigationBottom.visibility = View.VISIBLE

                else -> binding.navigationBottom.visibility = View.GONE
            }
        }
    }
}