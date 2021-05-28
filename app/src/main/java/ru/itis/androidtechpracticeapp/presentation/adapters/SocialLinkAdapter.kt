package ru.itis.androidtechpracticeapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.data.api.responses.SocialLinksResponse
import ru.itis.androidtechpracticeapp.presentation.holders.SocialLinkHolder

class SocialLinkAdapter(
    private val click: (SocialLinksResponse) -> Unit
) : ListAdapter<SocialLinksResponse, SocialLinkHolder>(
    object : DiffUtil.ItemCallback<SocialLinksResponse>() {
        override fun areItemsTheSame(oldItem: SocialLinksResponse, newItem: SocialLinksResponse): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SocialLinksResponse, newItem: SocialLinksResponse): Boolean =
            oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialLinkHolder =
        SocialLinkHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_social_link, parent, false),
            click
        )

    override fun onBindViewHolder(holder: SocialLinkHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: List<SocialLinksResponse>?) {
        super.submitList(if (list != null) ArrayList(list) else list)
    }

}