package ru.itis.androidtechpracticeapp.presentation.holders

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse
import ru.itis.androidtechpracticeapp.data.api.responses.SocialLinksResponse

class SocialLinkHolder(
    containerView: View,
) : RecyclerView.ViewHolder(containerView) {

    private var title: TextView = itemView.findViewById(R.id.item_social_link_title)
    private var link: TextView = itemView.findViewById(R.id.item_social_link_link)

    fun bind(socialLink: SocialLinksResponse) {
        title.text = socialLink.titleOfSocialRecourse
        link.text = socialLink.socialLink // todo
    }

}
