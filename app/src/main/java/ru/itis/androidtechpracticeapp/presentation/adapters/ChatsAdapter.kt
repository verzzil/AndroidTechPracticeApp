package ru.itis.androidtechpracticeapp.presentation.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.holders.ChatsHolder
import ru.itis.androidtechpracticeapp.presentation.models.ChatPresentation

class ChatsAdapter(
    private val click: (ChatPresentation) -> Unit
) : ListAdapter<ChatPresentation, ChatsHolder>(
    object : DiffUtil.ItemCallback<ChatPresentation>() {
        override fun areItemsTheSame(
            oldItem: ChatPresentation,
            newItem: ChatPresentation
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ChatPresentation,
            newItem: ChatPresentation
        ): Boolean =
            oldItem.lastMessage.text == newItem.lastMessage.text

        override fun getChangePayload(oldItem: ChatPresentation, newItem: ChatPresentation): Any? {
            val bundle = Bundle().apply {
                if (oldItem.lastMessage.text != newItem.lastMessage.text) {
                    putString("LASTMESSAGE", newItem.lastMessage.text)
                    if (oldItem.chatType == "GROUP") {
                        putString("USERNAME",newItem.lastMessage.userName)
                    }
                }
            }
            return if (bundle.isEmpty) null else bundle
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsHolder =
        ChatsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chat, parent, false),
            click
        )

    override fun onBindViewHolder(holder: ChatsHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: List<ChatPresentation>?) {
        super.submitList(if (list != null) ArrayList(list) else list)
    }

    override fun onBindViewHolder(holder: ChatsHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            (payloads[0] as? Bundle)?.also {
                holder.updateFields(it)
            }
        }

    }
}