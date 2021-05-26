package ru.itis.androidtechpracticeapp.presentation.holders

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation

class AddToGroupHolder(
    containerView: View,
    private val click: (UserPresentation) -> Unit,
): RecyclerView.ViewHolder(containerView) {

    private var title: TextView = itemView.findViewById(R.id.item_add_user_name)
    private var photo: ShapeableImageView = itemView.findViewById(R.id.item_add_user_photo)

    fun bind(user: UserPresentation) {
        // todo PHOTO

        title.text = user.getFullName()
//        photo.setImageResource()

        itemView.setOnClickListener {
            click(user)
        }
    }

}