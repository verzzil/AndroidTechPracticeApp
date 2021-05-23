package ru.itis.androidtechpracticeapp.presentation.holders

import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.models.PostPresentation

class PostHolder(
    containerView: View,
    private val click: (news: PostPresentation) -> Unit
) : RecyclerView.ViewHolder(containerView) {

    private var postTitle: AppCompatTextView = itemView.findViewById(R.id.item_news_straggered_title)

    fun bind(news: PostPresentation) {
        postTitle.text = news.title

        itemView.setOnClickListener {
            click(news)
        }
    }

}