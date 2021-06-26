package com.example.bridal.ui.settingsaccountfragment

import android.content.Context
import android.content.res.Configuration
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bridal.ui.glideLoader
import com.example.bridal.util.Constants
import java.util.*

class SettingsAccountViewModel : ViewModel() {


    var tvName          = MutableLiveData<String>("")
    var tvEmail         = MutableLiveData<String>("")
    var tvPhoneNumber   = MutableLiveData<String>("")


    fun showDataForUser(context: Context , iv_profile : ImageView){
        val myPreference = context.getSharedPreferences(Constants.USERS_SHARED_KEY,Context.MODE_PRIVATE)
        tvName.value        =   "${myPreference!!.getString(Constants.FIRST_NAME_KEY,"")} "+
                                "${myPreference!!.getString(Constants.LAST_NAME_KEY,"")}"

        tvEmail.value       = myPreference!!.getString(Constants.USER_EMAIL_KEY,"")
        tvPhoneNumber.value = myPreference!!.getString(Constants.USER_MOBILE_KEY,"")

        glideLoader(context).loadUserPicture(myPreference!!.getString(Constants.USER_PROFILE_IMAGE,"").toString(),iv_profile)
    }

    fun loadLocated( context: Context){
        val sharedPref  = context.getSharedPreferences("setting",Context.MODE_PRIVATE)
        val language    = sharedPref.getString("myLanguage","")
        setLocate(context ,language.toString())
    }

    @Suppress("DEPRECATION")
    fun setLocate(context: Context, language : String) {
        val local = Locale(language)
        Locale.setDefault(local)
        val config = Configuration()
        config.locale = local
        context.resources.updateConfiguration(config,context.resources.displayMetrics)

        val sharedPref = context.getSharedPreferences("setting", Context.MODE_PRIVATE)!!.edit()
        sharedPref.putString("myLanguage",language).apply()
    }
}