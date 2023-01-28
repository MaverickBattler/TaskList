package com.practice.tasklist

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel (private val dao: TaskDao): ViewModel() {
    var newTaskName = ""

    val tasks = dao.getAll()

    // Add new task
    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.name = newTaskName
            dao.insert(task)
        }
    }
}