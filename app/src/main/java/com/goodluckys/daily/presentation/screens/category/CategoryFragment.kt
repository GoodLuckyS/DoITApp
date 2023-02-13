package com.goodluckys.daily.presentation.screens.category

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goodluckys.daily.databinding.FragmentCategoryBinding
import com.goodluckys.daily.presentation.adapter.TaskListAdapter
import com.goodluckys.daily.presentation.base.BaseFragment


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(
    FragmentCategoryBinding::inflate
) {

    override val viewModel: CategoryViewModel by viewModels {
        appComponent.viewModelFactory()
    }

    private val args: CategoryFragmentArgs by navArgs()

    private lateinit var taskListAdapter: TaskListAdapter

    override fun initialize() {
        viewModel.getTaskList(args.id)
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        taskListAdapter = TaskListAdapter()
        val lm1 = LinearLayoutManager(context)
        lm1.orientation = LinearLayoutManager.VERTICAL
        binding.rcTask.adapter = taskListAdapter
        binding.rcTask.layoutManager = lm1

        taskListAdapter.onLongClickListener = {
               viewModel.deleteTask(it)
        }

        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = taskListAdapter.currentList[viewHolder.position]
                Log.e("SOAP",item.toString())
                viewModel.deleteTask(item)

            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rcTask)

                                  
        

    }

    override fun setupListeners() {
        viewModel.uiState.collectUIState(
            onError = {
                Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                Toast.makeText(this.context, "SUCA", Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun setupSubscribes() {
        viewModel.taskList.observe {
            taskListAdapter.submitList(it)
        }
    }


}