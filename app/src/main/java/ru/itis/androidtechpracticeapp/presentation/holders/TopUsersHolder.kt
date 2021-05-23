package ru.itis.androidtechpracticeapp.presentation.holders

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.custom.NumberFromTopTextView
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation

class TopUsersHolder(
    containerView: View,
    private val click: (user: UserPresentation) -> Unit
) : RecyclerView.ViewHolder(containerView) {

    private var userFullName: AppCompatTextView = itemView.findViewById(R.id.item_top_user_title)
    private var userPlace: NumberFromTopTextView = itemView.findViewById(R.id.item_top_user_place)

    @SuppressLint("ResourceAsColor")
    fun bind(user: UserPresentation) {


//        when(user.placeFromTop) {
//            1 -> userPlace.setDataColor(Color.parseColor("#FFD700"))
//            2 -> userPlace.setDataColor(Color.parseColor("#314552"))
//            3 -> userPlace.setDataColor(Color.parseColor("#CD7F32"))
//        }

        userFullName.text = user.getFullName()
        itemView.setOnClickListener {
            click(user)
        }
    }
}