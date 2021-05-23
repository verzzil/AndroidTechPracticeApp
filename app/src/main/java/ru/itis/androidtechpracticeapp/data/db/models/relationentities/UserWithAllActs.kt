package ru.itis.androidtechpracticeapp.data.db.models.relationentities

import androidx.room.Embedded
import androidx.room.Relation
import ru.itis.androidtechpracticeapp.data.db.models.UserActsDb
import ru.itis.androidtechpracticeapp.data.db.models.UserDb

data class UserWithAllActs(
    @Embedded
    val user: UserDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "authorId",
        entity = UserActsDb::class
    )
    val acts: List<UserActsDb>
)
