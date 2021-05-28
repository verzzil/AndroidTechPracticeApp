package ru.itis.androidtechpracticeapp.presentation.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.data.api.responses.SocialLinksResponse

class SocialLinkHolder(
    containerView: View,
    private val click: (SocialLinksResponse) -> Unit
) : RecyclerView.ViewHolder(containerView) {

    private var image: ImageView = itemView.findViewById(R.id.item_social_link_image)
    private var link: TextView = itemView.findViewById(R.id.item_social_link_link)

    fun bind(socialLink: SocialLinksResponse) {
        if (socialLink.titleOfSocialRecourse == "VK") {
            image.setImageResource(R.drawable.vk)
            link.text = "vk.com"
        } else if (socialLink.titleOfSocialRecourse == "Instagram") {
            image.setImageResource(R.drawable.instagram)
            link.text = "instagram.com"
        } else {
            image.setImageResource(R.drawable.facebook)
            link.text = "facebook.com"
        }

        link.setOnClickListener {
            click(socialLink)
        }

    }

}
