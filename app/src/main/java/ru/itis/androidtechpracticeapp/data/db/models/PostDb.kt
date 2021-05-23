package ru.itis.androidtechpracticeapp.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.itis.androidtechpracticeapp.data.api.responses.PostResponse

@Entity(tableName = "posts")
data class PostDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val authorId: Int
) {

    companion object {
        fun from(post: PostResponse): PostDb =
            PostDb(
                id = post.id,
                title = post.title,
                description = post.content,
                authorId = post.authorId
            )

        fun fromList(posts: List<PostResponse>): List<PostDb> =
            posts.map(::from)
    }

}
