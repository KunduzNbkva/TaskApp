package com.example.taskapp.extentions

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun View.loadImage(uri: Uri){
    Glide
        .with(this)
        .load(uri)
        .centerCrop()
        .into(this as ImageView)
}

fun Fragment.showToast(message:String){
    Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT ).show()
}


