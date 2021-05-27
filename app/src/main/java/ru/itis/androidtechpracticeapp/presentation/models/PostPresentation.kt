package ru.itis.androidtechpracticeapp.presentation.models

import android.graphics.Bitmap
import ru.itis.androidtechpracticeapp.domain.models.PostDomain

data class PostPresentation(
    val id: Int,
    var title: String,
    val desc: String,
    val author: UserPresentation,
    val link: String,
    var bitmap: Bitmap?
) {

    companion object {
        fun from(post: PostDomain): PostPresentation =
            PostPresentation(
                post.id,
                post.title,
                post.desc,
                UserPresentation.from(post.author),
                post.link,
                null
            )

        fun fromList(posts: List<PostDomain>): List<PostPresentation> =
            posts.map(::from)
    }

}