package com.practice.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.practice.tasklist.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root
        // Get a reference to the current application
        val application = requireNotNull(this.activity).application
        // Build the database (if it doesn't already exist)
        // and get a reference to the taskDao property
        val dao = TaskDatabase.getInstance(application).taskDao
        // With the help of the ViewModelFactory
        val viewModelFactory = TasksViewModelFactory(dao)
        // Get the view model using ViewModelProvider
        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[TasksViewModel::class.java]
        // Set the view model in the layout
        binding.viewModel = viewModel
        // Set the layout's lifecycle owner so it can respond to live data updates
        binding.lifecycleOwner = viewLifecycleOwner

        // Add the adapter to the recyclerview
        val adapter = TaskItemAdapter()
        binding.tasksRecview.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner) {
            it?.let {
                adapter.data = it
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}