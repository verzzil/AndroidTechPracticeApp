package ru.itis.androidtechpracticeapp.data.db.models.relationentities

import androidx.room.Embedded
import androidx.room.Relation
import ru.itis.androidtechpracticeapp.data.db.models.MessageDb
import ru.itis.androidtechpracticeapp.data.db.models.UserDb

data class UserWithAllMessages(
    @Embedded
    val user: UserDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId",
        entity = MessageDb::class
    )
    val messages: List<MessageDb>
)