package com.example.taskapp.database.local

import androidx.room.*
import com.example.taskapp.ui.models.TaskModel

@Dao
interface TaskDao {

    @Query("SELECT * FROM TaskModel ORDER BY date DESC")
    fun getAllTasks() :  MutableList<TaskModel>

    @Insert
    fun insert(task: TaskModel)

    @Delete
    fun deleteTask(task: TaskModel)

    @Query("SELECT * FROM TaskModel ORDER BY title ASC")
    fun getTasksFromA(): MutableList<TaskModel>

    @Query("SELECT * FROM TaskModel ORDER BY title DESC")
    fun getTasksFromZ(): MutableList<TaskModel>

    @Query("SELECT * FROM TaskModel ORDER BY date ASC")
    fun getTasksByDate(): MutableList<TaskModel>

    @Update()
    fun updateTask(task: TaskModel)

}