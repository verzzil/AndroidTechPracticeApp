package ru.itis.androidtechpracticeapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.data.api.responses.ActProofResponse
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse
import ru.itis.androidtechpracticeapp.presentation.holders.ActHolder
import ru.itis.androidtechpracticeapp.presentation.holders.AdminFrHolder

class AdminFrAdapter(
    private val click: (ActProofResponse) -> Unit
) : ListAdapter<ActProofResponse, AdminFrHolder>(
    object : DiffUtil.ItemCallback<ActProofResponse>() {
        override fun areItemsTheSame(oldItem: ActProofResponse, newItem: ActProofResponse): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ActProofResponse, newItem: ActProofResponse): Boolean =
            oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminFrHolder =
        AdminFrHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_admin, parent, false),
            click
        )

    override fun onBindViewHolder(holder: AdminFrHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: List<ActProofResponse>?) {
        super.submitList(if (list != null) ArrayList(list) else list)
    }
}