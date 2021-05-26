package ru.itis.androidtechpracticeapp.presentation.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation

class AddedToGroupHolder(
    containerView: View,
    private val click: (UserPresentation) -> Unit,
): RecyclerView.ViewHolder(containerView) {

    val name: TextView = itemView.findViewById(R.id.added_group_user_name)

    fun bind(user: UserPresentation) {

        name.text = user.getFullName()

        itemView.setOnClickListener {
            click(user)
        }
    }

}