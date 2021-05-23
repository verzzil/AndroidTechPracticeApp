package ru.itis.androidtechpracticeapp.data.db.models

import androidx.room.PrimaryKey

data class SocialLinkDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val titleOfSocialRecourse: String,
    val socialLink: String
)
