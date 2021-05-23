package ru.itis.androidtechpracticeapp.presentation.models

import ru.itis.androidtechpracticeapp.domain.models.PostDomain

data class PostPresentation(
    val id: Int,
    val title: String,
    val desc: String,
    val author: UserPresentation,
) {

    companion object {
        fun from(post: PostDomain): PostPresentation =
            PostPresentation(
                post.id,
                post.title,
                post.desc,
                UserPresentation.from(post.author)
            )

        fun fromList(posts: List<PostDomain>): List<PostPresentation> =
            posts.map(::from)
    }

}