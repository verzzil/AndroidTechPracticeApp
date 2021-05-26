package ru.itis.androidtechpracticeapp.presentation.holders

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.custom.NumberFromTopTextView
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation

class TopUsersHolder(
    containerView: View,
    private val click: (user: UserPresentation) -> Unit
) : RecyclerView.ViewHolder(containerView) {

    private var userFullName: TextView = itemView.findViewById(R.id.item_top_user_card_name)
    private var userCash: TextView = itemView.findViewById(R.id.item_top_user_card_cash)

    @SuppressLint("ResourceAsColor")
    fun bind(user: UserPresentation) {
        userFullName.text = user.getFullName()
        userCash.text = "${user.cash} KindCoin"

        itemView.setOnClickListener {
            click(user)
        }
    }
}