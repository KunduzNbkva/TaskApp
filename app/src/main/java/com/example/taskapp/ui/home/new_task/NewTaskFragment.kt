package com.example.taskapp.ui.home.new_task

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.databinding.FragmentNewTaskBinding
import com.example.taskapp.ui.models.TaskModel
import java.text.SimpleDateFormat
import java.util.*

class NewTaskFragment : Fragment() {
    private lateinit var binding: FragmentNewTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTaskBinding.inflate(inflater,container,false)
        initViews()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
      binding.btnSave.setOnClickListener {
//          setFragmentResult(TASK_KEY, bundleOf(
//              "title" to binding.etTitle.text.toString(),
//              "desc" to binding.etDesc.text.toString()
//          ))
          App.db.taskDao().insert(
              TaskModel(
              title = binding.etTitle.text.toString(),
              desc = binding.etDesc.text.toString(),
              date = SimpleDateFormat("dd/M/yyyy hh:mm").format(Date())
          ))

          Log.e("ololo","Room inserted successfully!")
          findNavController().navigateUp()
      }
    }

    private fun initViews() {

    }

    companion object{
        const val TASK_KEY = "new_task"
    }

}