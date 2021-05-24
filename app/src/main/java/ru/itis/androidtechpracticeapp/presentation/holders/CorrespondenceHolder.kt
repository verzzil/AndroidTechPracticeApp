package ru.itis.androidtechpracticeapp.presentation.holders

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.models.MessagePresentation

class CorrespondenceHolder(
    containerView: View,
    private val userId: Int
) : RecyclerView.ViewHolder(containerView) {

    private val myMessage: ConstraintLayout = itemView.findViewById(R.id.item_message_my_msg)
    private val myMessageText: TextView = itemView.findViewById(R.id.item_message_my_msg_text)
    private val friendMessage: ConstraintLayout =
        itemView.findViewById(R.id.item_message_friend_msg)
    private val friendMessageAuthor: TextView =
        itemView.findViewById(R.id.item_message_friend_msg_author)
    private val friendMessageText: TextView =
        itemView.findViewById(R.id.item_message_friend_msg_text)


    fun bind(msg: MessagePresentation) {
        myMessage.visibility = View.INVISIBLE
        friendMessageAuthor.text = msg.userName
        friendMessageText.text = msg.text
//        if (msg.userId == userId) {
//            friendMessage.visibility = View.INVISIBLE
//            myMessageText.text = msg.text
//        } else {
//            Log.i("blockelse", "else")
//            myMessage.visibility = View.INVISIBLE
//            friendMessageAuthor.text = msg.userName
//            friendMessageText.text = msg.text
//        }
    }

}