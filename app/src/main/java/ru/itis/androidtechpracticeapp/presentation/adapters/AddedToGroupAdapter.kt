package ru.itis.androidtechpracticeapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse
import ru.itis.androidtechpracticeapp.presentation.holders.AddToGroupHolder
import ru.itis.androidtechpracticeapp.presentation.holders.AddedToGroupHolder
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation

class AddedToGroupAdapter(
    private val click: (UserPresentation) -> Unit
) : ListAdapter<UserPresentation, AddedToGroupHolder>(
object: DiffUtil.ItemCallback<UserPresentation>() {
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddedToGroupHolder =
        AddedToGroupHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_added_users, parent, false),
            click
        )

    override fun onBindViewHolder(holder: AddedToGroupHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: List<UserPresentation>?) {
        super.submitList(if (list != null) ArrayList(list) else list)
    }
}