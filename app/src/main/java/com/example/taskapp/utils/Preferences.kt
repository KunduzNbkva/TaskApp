package com.example.taskapp.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import java.net.URI

class Preferences(context: Context) {
    val sharedPreference = context.getSharedPreferences("prefs ", Context.MODE_PRIVATE)

    var board: Boolean
    get()= sharedPreference.getBoolean("board",false)
    set(value) =  sharedPreference.edit().putBoolean("board",value).apply()


    var profileName:String?
    get() = sharedPreference.getString("name",null)
    set(value) = sharedPreference.edit().putString("name",value).apply()


    var imgProfile: String?
    get() = sharedPreference.getString("profile_image",null)
    set(value) =  sharedPreference.edit().putString("profile_image",value).apply()




}