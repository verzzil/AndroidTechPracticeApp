package ru.itis.androidtechpracticeapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.fragment_current_chat.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.holders.CorrespondenceHolder
import ru.itis.androidtechpracticeapp.presentation.models.MessagePresentation

class CorrespondenceAdapter(
    private val userId: Int
): ListAdapter<MessagePresentation, CorrespondenceHolder>(
    object : DiffUtil.ItemCallback<MessagePresentation>() {
        override fun areItemsTheSame(
            oldItem: MessagePresentation,
            newItem: MessagePresentation
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: MessagePresentation,
            newItem: MessagePresentation
        ): Boolean =
            oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CorrespondenceHolder =
        CorrespondenceHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message, parent, false),
            userId
        )

    override fun onBindViewHolder(holder: CorrespondenceHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: List<MessagePresentation>?) {
        super.submitList(if (list != null) ArrayList(list) else list)
    }

}