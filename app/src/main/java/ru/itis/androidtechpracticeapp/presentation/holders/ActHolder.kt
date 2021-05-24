package ru.itis.androidtechpracticeapp.presentation.holders

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse
import ru.itis.androidtechpracticeapp.presentation.models.TestTask

class ActHolder(
    containerView: View,
    private val click: (task: ActResponse) -> Unit,
) : RecyclerView.ViewHolder(containerView) {

    private var title: TextView = itemView.findViewById(R.id.item_task_title)
    private var status: TextView = itemView.findViewById(R.id.item_task_status)
    private var btn: Button = itemView.findViewById(R.id.item_task_send_proof)

    fun bind(task: ActResponse) {
        title.text = task.title
        status.text = task.actStatus

        if (task.isAdmin == false) {
            btn.setBackgroundColor(Color.GRAY)
        }

        itemView.setOnClickListener {
            click(task)
        }

    }

}