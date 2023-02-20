package com.example.taskapp.ui.onboard


data class BoardModel(
    val img: Int? = null,
    val title: String? = null,
    val desc:String? = null,
    val isLast: Boolean? = false
): java.io.Serializable
