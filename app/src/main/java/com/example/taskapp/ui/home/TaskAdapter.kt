package com.example.taskapp.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.TaskItemBinding
import com.example.taskapp.ui.models.TaskModel


class TaskAdapter(val onLongItemClick: OnLongItemClick,
                  val itemClickListener: OnItemClickListener): RecyclerView.Adapter<TaskAdapter.TaskHolder>() {
  private var taskList = mutableListOf<TaskModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        return TaskHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun editTask(taskModel: TaskModel,position: Int){
        taskList[position] = taskModel
        notifyItemChanged(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAllTasksRoom(list:MutableList<TaskModel>){
        taskList = list
        notifyDataSetChanged()
    }

    fun getItem(position: Int):TaskModel{
        return  taskList[position]
    }

    fun removeItem(position: Int){
        taskList.removeAt(position)
        notifyItemRemoved(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(fromA: Boolean ) {
        if(fromA) {
            taskList.clear()
            taskList.addAll(App.db.taskDao().getTasksFromA())
            Log.e("ololo","filter by letter A")
        } else {
            taskList.clear()
            taskList.addAll(App.db.taskDao().getTasksFromZ())
            Log.e("ololo","filter by letter Z")
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterTasksByDate(){
        taskList.clear()
        taskList.addAll(App.db.taskDao().getTasksByDate())
        Log.e("ololo","filter by date ")
        notifyDataSetChanged()
    }


    inner class TaskHolder(private var binding: TaskItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
            if(adapterPosition % 2 == 0) {
                binding.root.setBackgroundResource(R.color.light_gray)
            } else {
                binding.root.setBackgroundResource(R.color.white)
            }
            binding.tvTitleItem.text = taskModel.title
            binding.tvTitleDesc.text = taskModel.desc
            binding.tvTitleDate.text = taskModel.date

            binding.root.setOnLongClickListener {
                onLongItemClick.longClick(adapterPosition)
                true
            }

            binding.root.setOnClickListener {
                itemClickListener.onItemClick(taskModel,adapterPosition)
            }
        }
    }

}


interface OnItemClickListener {
    fun onItemClick( task: TaskModel,position: Int)
}

interface  OnLongItemClick{
    fun longClick(position: Int)
}


