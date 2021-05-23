package ru.itis.androidtechpracticeapp.data.db.models.relationentities

import androidx.room.Embedded
import androidx.room.Relation
import ru.itis.androidtechpracticeapp.data.db.models.UserDb
import ru.itis.androidtechpracticeapp.data.db.models.UserPhotoDb

data class UserWithAllPhotos(
    @Embedded
    val user: UserDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId",
        entity = UserPhotoDb::class
    )
    val photos: List<UserPhotoDb>
)
