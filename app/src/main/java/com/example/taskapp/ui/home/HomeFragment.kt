package com.example.taskapp.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.extentions.showToast
import com.example.taskapp.ui.models.TaskModel

class HomeFragment : Fragment(), OnLongItemClick{
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
        taskAdapter = TaskAdapter(this)
    }

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

//        setFragmentResultListener(TASK_KEY){_,bundle ->
//            Log.e("ololo","initViews: "+bundle.getString("title")
//                    +bundle.getString("desc"))
//
//            val title = bundle.getString("title")
//            val desc = bundle.getString("desc")
 //       taskAdapter.addTask(TaskModel())
        getDataFromLocalDB()

        }

    private fun getDataFromLocalDB(){
       val listAllTasks =  App.db.taskDao().getAllTasks()
        taskAdapter.addAllTasksRoom(listAllTasks)
    }

    override fun longClick(position: Int) {
        val builder = AlertDialog.Builder(binding.root.context)
        builder.setTitle("Deleting")
        builder.setMessage("Do you want to delete this task?")

        builder.setPositiveButton("OK") { dialog, which ->
            App.db.taskDao().deleteTask(taskAdapter.getItem(position))
            taskAdapter.removeItem(position)
        }

        builder.setNegativeButton("NO") { dialog, which ->
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
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_sort_by_a -> {
                        taskAdapter.filter(true)
                        showToast("Filtered from A to Z")
                        true
                    }
                    R.id.action_sort_by_z -> {
                        taskAdapter.filter(false)
                        showToast("Filtered from Z to A")
                        true
                    }
                    R.id.action_sort_by_date -> {
                        taskAdapter.filterTasksByDate()
                        showToast("Filtered by date")
                        true
                    }
                    else -> false
                }
            }

            override fun onMenuClosed(menu: Menu) {
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }



}