package com.practice.tasklist

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel (private val dao: TaskDao): ViewModel() {
    var newTaskName = ""

    private val tasks = dao.getAll()
    val tasksString = Transformations.map(tasks) {
        tasks -> formatTasks(tasks)
    }

    // Add new task
    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.name = newTaskName
            dao.insert(task)
        }
    }
    // Convert List<Task> to String
    private fun formatTasks(tasks: List<Task>): String {
        return tasks.fold("") {str, item -> str + '\n' + formatTask(item)}
    }
    // Convert one Task to String
    private fun formatTask(task: Task): String {
        var str = "ID: ${task.id}"
        str += '\n' + "Задание: ${task.name}"
        str += '\n' + "Сделано: ${if (task.isDone) "Да" else "Нет"}" + "\n"
        return str
    }
}