package com.practice.tasklist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter : RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {
    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder =
        TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class TaskItemViewHolder(rootView: CardView) : RecyclerView.ViewHolder(rootView) {
        private val taskNameTextview: TextView = rootView.findViewById(R.id.task_name_textview)
        private val taskDoneCheckbox: CheckBox = rootView.findViewById(R.id.task_done_checkbox)
        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(
                    R.layout.task_item,
                    parent, false
                ) as CardView
                return TaskItemViewHolder(view)
            }
        }

        fun bind(item: Task) {
            taskNameTextview.text = item.name
            taskDoneCheckbox.isChecked = item.isDone
        }
    }
}