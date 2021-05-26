package ru.itis.androidtechpracticeapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.holders.TopUsersHolder
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation

class TopUsersAdapter(
    private val click: (user: UserPresentation) -> Unit
) : ListAdapter<UserPresentation, TopUsersHolder>(
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopUsersHolder =
        TopUsersHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_top_users_with_card, parent, false),
            click
        )

    override fun onBindViewHolder(holder: TopUsersHolder, position: Int) =
        holder.bind(getItem(position))


}