package ru.itis.androidtechpracticeapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.holders.ActHolder
import ru.itis.androidtechpracticeapp.presentation.holders.AddToGroupHolder
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation

class AddToGroupAdapter(
    private val click: (UserPresentation) -> Unit,
) : ListAdapter<UserPresentation, AddToGroupHolder>(
    object : DiffUtil.ItemCallback<UserPresentation>() {
        override fun areItemsTheSame(
            oldItem: UserPresentation,
            newItem: UserPresentation,
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: UserPresentation,
            newItem: UserPresentation,
        ): Boolean =
            oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToGroupHolder =
        AddToGroupHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_add_to_group, parent, false),
            click
        )

    override fun onBindViewHolder(holder: AddToGroupHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: List<UserPresentation>?) {
        super.submitList(if (list != null) ArrayList(list) else list)
    }

}
