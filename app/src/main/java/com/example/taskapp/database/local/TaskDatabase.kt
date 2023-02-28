package com.example.taskapp.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskapp.ui.models.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}