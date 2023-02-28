package com.example.taskapp

import android.app.Application
import androidx.room.Room
import com.example.taskapp.database.local.TaskDatabase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            TaskDatabase::class.java,
            "task_database")
            .allowMainThreadQueries()
            .build()
    }


    companion object{
        lateinit var db:TaskDatabase
    }
}