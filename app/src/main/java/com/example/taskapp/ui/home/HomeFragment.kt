package com.example.taskapp.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.extentions.showToast
import com.example.taskapp.ui.home.new_task.NewTaskFragment
import com.example.taskapp.ui.models.TaskModel

class HomeFragment : Fragment(), OnLongItemClick, OnItemClickListener{
   private lateinit var binding:FragmentHomeBinding
   private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FragmentHomeBinding.inflate(inflater, container,false)
        initViews()
        initListeners()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter(this,this)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        taskAdapter.notifyDataSetChanged()
    }

    private fun initListeners() {
        binding.fabHome.setOnClickListener {
            findNavController().navigate(R.id.navigation_new_task)
        }
    }

    private fun initViews() {
        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = taskAdapter
        getDataFromLocalDB()

    }

    private fun getDataFromLocalDB(){
       val listAllTasks =  App.db.taskDao().getAllTasks()
        taskAdapter.addAllTasksRoom(listAllTasks)
    }

    override fun longClick(position: Int) {
        val builder = AlertDialog.Builder(binding.root.context)
        builder.setTitle(getString(R.string.deleting))
        builder.setMessage(getString(R.string.wanna_delete))

        builder.setPositiveButton(getString(R.string.ok)) { _, _ ->
            App.db.taskDao().deleteTask(taskAdapter.getItem(position))
            taskAdapter.removeItem(position)
        }

        builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {

            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.action_bar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_sort_by_a -> {
                        taskAdapter.filter(true)
                        showToast(getString(R.string.filter_from_a_z))
                        true
                    }
                    R.id.action_sort_by_z -> {
                        taskAdapter.filter(false)
                        showToast(getString(R.string.filter_from_z_a))
                        true
                    }
                    R.id.action_sort_by_date -> {
                        taskAdapter.filterTasksByDate()
                        showToast(getString(R.string.filter_by_date))
                        true
                    }
                    else -> false
                }
            }

            override fun onMenuClosed(menu: Menu) {
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        setFragmentResultListener(EDITED_TASK) { _, bundle ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                taskAdapter.editTask(bundle.getSerializable(NewTaskFragment.TASK, TaskModel::class.java)!!, bundle.getInt(NewTaskFragment.POSITION))
            }else{
                taskAdapter.editTask(bundle.getSerializable(NewTaskFragment.TASK) as TaskModel, bundle.getInt(NewTaskFragment.POSITION))
            }
        }
    }

    override fun onItemClick(task: TaskModel, position: Int) {
        if (task.id != null){
            findNavController().navigate(R.id.navigation_new_task, bundleOf(NewTaskFragment.TASK to task, NewTaskFragment.POSITION to position))
        }
    }

    companion object {
        const val EDITED_TASK = "edited_task"
    }
}