package com.example.taskapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.databinding.TaskItemBinding


class TaskAdapter: RecyclerView.Adapter<TaskAdapter.TaskHolder>() {
  private var taskList = arrayListOf<TaskModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        return TaskHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun addTask(taskModel: TaskModel){
        taskList.add(taskModel)
    }



    inner class TaskHolder(private var binding: TaskItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
            binding.tvTitleItem.text = taskModel.title
            binding.tvTitleDesc.text = taskModel.desc
        }
    }

}