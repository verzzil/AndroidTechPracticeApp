package ru.itis.androidtechpracticeapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.holders.PostHolder
import ru.itis.androidtechpracticeapp.presentation.models.PostPresentation

class NewsAdapter(
    private val click: (news: PostPresentation) -> Unit
) : ListAdapter<PostPresentation, PostHolder>(
    object: DiffUtil.ItemCallback<PostPresentation>() {
        override fun areItemsTheSame(
            oldItem: PostPresentation,
            newItem: PostPresentation
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: PostPresentation,
            newItem: PostPresentation
        ): Boolean =
            oldItem == newItem

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder =
        PostHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news_straggered, parent, false),
            click
        )

    override fun onBindViewHolder(holder: PostHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: List<PostPresentation>?) {
        super.submitList(if (list != null) ArrayList(list) else list)
    }
}