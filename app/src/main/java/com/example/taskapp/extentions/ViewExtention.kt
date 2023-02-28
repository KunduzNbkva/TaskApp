package com.example.taskapp.extentions

import android.content.Context
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun View.loadImage(uri: Uri){
    Glide
        .with(this)
        .load(uri)
        .centerCrop()
        .into(this as ImageView);
}

fun Fragment.showToast(message:String){
    Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT ).show()
}

fun View.alert(context:Context, alertName: String, alertMessage:String,
               positiveChoice:String,negativeChoice:String,
               posAction: Unit, negAction: Unit
){
    val builder = AlertDialog.Builder(context)
    builder.setTitle(alertName)
    builder.setMessage(alertMessage)

    builder.setPositiveButton(positiveChoice) { dialog, which ->
        Toast.makeText(context,
            positiveChoice, Toast.LENGTH_SHORT).show()
    }

    builder.setNegativeButton(negativeChoice) { dialog, which ->
        Toast.makeText(context,
            negativeChoice, Toast.LENGTH_SHORT).show()
    }

    builder.show()
}

