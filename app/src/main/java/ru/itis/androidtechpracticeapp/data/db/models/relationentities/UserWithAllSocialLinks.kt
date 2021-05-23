package ru.itis.androidtechpracticeapp.data.db.models.relationentities

import androidx.room.Embedded
import androidx.room.Relation
import ru.itis.androidtechpracticeapp.data.db.models.SocialLinkDb
import ru.itis.androidtechpracticeapp.data.db.models.UserDb

data class UserWithAllSocialLinks(
    @Embedded
    val user: UserDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId",
        entity = SocialLinkDb::class
    )
    val socialLinks: List<SocialLinkDb>
)
