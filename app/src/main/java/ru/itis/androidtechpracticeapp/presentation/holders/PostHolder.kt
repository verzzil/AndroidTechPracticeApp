package ru.itis.androidtechpracticeapp.presentation.holders

import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.models.PostPresentation
import java.lang.Exception
import java.net.URL

class PostHolder(
    containerView: View,
    private val click: (news: PostPresentation) -> Unit,
) : RecyclerView.ViewHolder(containerView) {

    private var postTitle: AppCompatTextView =
        itemView.findViewById(R.id.item_news_straggered_title)
    private var author: AppCompatTextView = itemView.findViewById(R.id.item_news_straggered_author)
    private var image: AppCompatImageView = itemView.findViewById(R.id.news_background_image)

    fun bind(news: PostPresentation) {
        if (news.bitmap != null) {
            image.setImageBitmap(news.bitmap)
        }
        if (news.title.length > 20) {
            news.title = "${news.title.substring(0, 20)}..."
        }
        postTitle.text = news.title
        author.text = news.author.getFullName()

        itemView.setOnClickListener {
            click(news)
        }
    }

}