package com.example.taskapp.ui.home.new_task

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentNewTaskBinding
import com.example.taskapp.ui.models.TaskModel
import java.text.SimpleDateFormat
import java.util.*

class NewTaskFragment : Fragment() {
    private lateinit var binding: FragmentNewTaskBinding
    private  var task: TaskModel? = null
    private  var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskBinding.inflate(inflater,container,false)
        initViews()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
      binding.btnSave.setOnClickListener {
           if(task != null){
               App.db.taskDao().updateTask(TaskModel(
                   id = task?.id,
                   title = binding.etTitle.text.toString(),
                   desc = binding.etDesc.text.toString(),
                   date = task?.date
               ))
           } else {
               App.db.taskDao().insert(
                   TaskModel(
                       title = binding.etTitle.text.toString(),
                       desc = binding.etDesc.text.toString(),
                       date = SimpleDateFormat(getString(R.string.date_format), Locale.getDefault()).format(Date())
                   ))
           }

          setFragmentResult(RESULT, bundleOf(TASK to task, POSITION to pos))
          findNavController().navigateUp()
          }
      }


    private fun initViews() {
        if (arguments!=null){
            task = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getSerializable(TASK, TaskModel::class.java)
            }else{
                arguments?.getSerializable(TASK) as TaskModel
            }
            binding.etTitle.setText(task?.title.toString())
            binding.etDesc.setText(task?.desc.toString())
            binding.btnSave.text = getString(R.string.edit_task)
            pos = arguments?.getInt(POSITION)?:0
        }



    }

    companion object {
        const val TASK = "task"
        const val POSITION = "position"
        const val RESULT = "result"
    }

}