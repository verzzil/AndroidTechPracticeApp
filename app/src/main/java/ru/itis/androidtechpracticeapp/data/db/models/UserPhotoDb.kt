package ru.itis.androidtechpracticeapp.data.db.models

import androidx.room.PrimaryKey

data class UserPhotoDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val link: String,
    val isAvatar: Boolean,
    val userId: Int
)
