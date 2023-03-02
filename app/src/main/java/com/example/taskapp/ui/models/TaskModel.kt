package com.example.taskapp.ui.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    var id : Long? = null,
    var title: String? = null,
    var desc: String? = null,
    var date: String? = null
)
