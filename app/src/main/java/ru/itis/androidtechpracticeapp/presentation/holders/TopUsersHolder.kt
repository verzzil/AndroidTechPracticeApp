package ru.itis.androidtechpracticeapp.presentation.holders

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation
import java.net.URL

class TopUsersHolder(
    containerView: View,
    private val click: (user: UserPresentation) -> Unit
) : RecyclerView.ViewHolder(containerView) {

    private var userFullName: TextView = itemView.findViewById(R.id.item_top_user_card_name)
    private var userCash: TextView = itemView.findViewById(R.id.item_top_user_card_cash)
    private var userPhoto: ImageView = itemView.findViewById(R.id.top_users_item_image)

    @SuppressLint("ResourceAsColor")
    fun bind(user: UserPresentation) {
        if(user.bitmap != null) {
            userPhoto.setImageBitmap(user.bitmap)
        }

        userFullName.text = user.getFullName()
        userCash.text = "${user.cash} KindCoin"

        itemView.setOnClickListener {
            click(user)
        }
    }
}