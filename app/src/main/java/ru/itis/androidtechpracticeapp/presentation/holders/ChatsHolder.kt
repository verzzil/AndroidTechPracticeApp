package ru.itis.androidtechpracticeapp.presentation.holders

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.models.ChatPresentation

class ChatsHolder(
    containerView: View,
    private val click: (ChatPresentation) -> Unit
) : RecyclerView.ViewHolder(containerView) {

    val userPhoto: ShapeableImageView =
        itemView.findViewById(R.id.messages_fragment_item_user_image)
    val chatTitle: AppCompatTextView = itemView.findViewById(R.id.messages_fragment_item_user_name)
    val lastMessage: AppCompatTextView =
        itemView.findViewById(R.id.messages_fragment_item_user_message)

    fun bind(chat: ChatPresentation) {
        // todo фотка!!!
        chatTitle.text = chat.title
        if (chat.chatType == "GROUP")
            lastMessage.text = "${chat.lastMessage.userName}: ${chat.lastMessage.text}"
        else
            lastMessage.text = chat.lastMessage.text

        itemView.setOnClickListener {
            click(chat)
        }
    }

    fun updateFields(bundle: Bundle) {
        if (bundle.containsKey("LASTMESSAGE")) {
            bundle.getString("LASTMESSAGE").also {lastMsg ->
                if (bundle.containsKey("USERNAME")) {
                    bundle.getString("USERNAME").also { userNm ->
                        lastMessage.text = "${userNm}: ${lastMsg}"
                    }
                } else {
                    lastMessage.text = "${lastMsg}"
                }
            }
        }
    }

}