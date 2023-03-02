package com.example.taskapp.ui.home

import android.util.Log
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.App
import com.example.taskapp.databinding.TaskItemBinding
import com.example.taskapp.ui.models.TaskModel


class TaskAdapter(val click: OnLongItemClick): RecyclerView.Adapter<TaskAdapter.TaskHolder>() {
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

    fun addTask(taskModel: TaskModel){
        taskList.add(taskModel)
    }

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

    fun filter(fromA: Boolean ) {
        if(fromA) {
            taskList.clear()
            taskList.addAll(App.db.taskDao().getTasksFromA())
            Log.e("ololo","filter works")
        } else {
            taskList.clear()
            taskList.addAll(App.db.taskDao().getTasksFromZ())
        }
        notifyDataSetChanged()
    }


    inner class TaskHolder(private var binding: TaskItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
            binding.tvTitleItem.text = taskModel.title
            binding.tvTitleDesc.text = taskModel.desc

            binding.root.setOnLongClickListener {
                click.longClick(adapterPosition)
                true
            }


        }
    }

}

interface  OnLongItemClick{
    fun longClick(position: Int)
}

