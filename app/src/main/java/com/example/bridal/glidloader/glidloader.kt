package com.example.bridal.ui

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.bridal.R

import java.io.IOException

class glideLoader(val context: Context) {

    /**
     * A function to load image from Uri or URL for the user profile picture.
     */
    fun loadUserPicture(image: Any, imageView: ImageView) {
        try {
            // Load the user image in the ImageView.
            Glide
                    .with(context)
                    .load(image) // Uri or URL of the image
                    .centerCrop() // Scale type of the image.
                    .placeholder(R.drawable.defult_cover_image) // A default place holder if image is failed to load.
                    .into(imageView) // the view in which the image will be loaded.
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}