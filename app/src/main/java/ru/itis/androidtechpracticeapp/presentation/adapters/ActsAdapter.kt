package ru.itis.androidtechpracticeapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse
import ru.itis.androidtechpracticeapp.presentation.holders.ActHolder

class ActsAdapter(
    private val click: (task: ActResponse) -> Unit,
) : ListAdapter<ActResponse, ActHolder>(
    object : DiffUtil.ItemCallback<ActResponse>() {
        override fun areItemsTheSame(oldItem: ActResponse, newItem: ActResponse): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ActResponse, newItem: ActResponse): Boolean =
            oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActHolder =
        ActHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false),
            click
        )

    override fun onBindViewHolder(holder: ActHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: List<ActResponse>?) {
        super.submitList(if (list != null) ArrayList(list) else list)
    }

}