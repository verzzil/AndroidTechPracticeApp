package ru.itis.androidtechpracticeapp.data.api.dto

data class SocialLinkDto(
    val id: Int?,
    val userId: Int,
    val titleOfSocialRecourse: String,
    val socialLink: String
)
