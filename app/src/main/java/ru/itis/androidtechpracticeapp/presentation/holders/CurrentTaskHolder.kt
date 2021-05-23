package ru.itis.androidtechpracticeapp.presentation.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.models.TestTask

class CurrentTaskHolder(
    containerView: View,
    private val click: (task: TestTask) -> Unit
) : RecyclerView.ViewHolder(containerView) {

    private var title: TextView = itemView.findViewById(R.id.tv_title_of_task)
    private var status: TextView = itemView.findViewById(R.id.tv_status_of_task)

    fun bind(task: TestTask) {
        title.text = task.title
        status.text = task.status

        itemView.setOnClickListener {
            click(task)
        }
    }

}