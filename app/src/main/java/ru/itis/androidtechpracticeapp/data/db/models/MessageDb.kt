package ru.itis.androidtechpracticeapp.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageDb(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var chatId: Int,
    var userId: Int,
    var sendDate: Long,
    var userName: String,
    var text: String
)
