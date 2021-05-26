package ru.itis.androidtechpracticeapp.presentation.holders

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.data.api.responses.ActProofResponse
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse

class AdminFrHolder(
    containerView: View,
    private val click: (ActProofResponse) -> Unit
): RecyclerView.ViewHolder(containerView) {

    private var title: TextView = itemView.findViewById(R.id.item_admin_title)
    private var btn: Button = itemView.findViewById(R.id.item_admin_decision)

    fun bind(act: ActProofResponse) {
        title.text = "${act.id}: ${act.type}"

        btn.setOnClickListener {
            click(act)
        }
    }

}