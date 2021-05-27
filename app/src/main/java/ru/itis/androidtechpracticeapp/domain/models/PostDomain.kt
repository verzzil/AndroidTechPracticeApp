package ru.itis.androidtechpracticeapp.domain.models

import ru.itis.androidtechpracticeapp.data.models.PostData
import ru.itis.androidtechpracticeapp.presentation.models.PostPresentation
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation

data class PostDomain(
    val id: Int,
    val title: String,
    val desc: String,
    val author: UserDomain,
    val link: String
) {

    companion object {
        fun from(post: PostData): PostDomain =
            PostDomain(
                post.id,
                post.title,
                post.desc,
                UserDomain.from(post.author),
                post.link
            )

        fun fromList(posts: List<PostData>): List<PostDomain> =
            posts.map(::from)
    }

}
