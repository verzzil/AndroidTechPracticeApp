package ru.itis.androidtechpracticeapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.holders.CurrentTaskHolder
import ru.itis.androidtechpracticeapp.presentation.models.TestTask

class CurrentTasksAdapter(
    private val tasks: List<TestTask>,
    private val click: (task: TestTask) -> Unit
) : RecyclerView.Adapter<CurrentTaskHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentTaskHolder =
        CurrentTaskHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_current_task, parent, false),
            click
        )

    override fun onBindViewHolder(holder: CurrentTaskHolder, position: Int) =
        holder.bind(tasks[position])

    override fun getItemCount(): Int =
        tasks.size
}