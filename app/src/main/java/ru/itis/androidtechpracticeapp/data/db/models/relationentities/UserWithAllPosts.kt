package ru.itis.androidtechpracticeapp.data.db.models.relationentities

import androidx.room.Embedded
import androidx.room.Relation
import ru.itis.androidtechpracticeapp.data.db.models.PostDb
import ru.itis.androidtechpracticeapp.data.db.models.UserDb

data class UserWithAllPosts(
    @Embedded
    val user: UserDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "authorId",
        entity = PostDb::class
    )
    val posts: List<PostDb>
)
