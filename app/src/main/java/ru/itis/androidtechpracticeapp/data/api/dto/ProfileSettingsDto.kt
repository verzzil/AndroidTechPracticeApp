package ru.itis.androidtechpracticeapp.data.api.dto

data class ProfileSettingsDto(
    val id: Int?,
    var firstName: String?,
    var lastName: String?,
    var oldPassword: String?,
    var newPassword: String?,
    var socialLinks: List<SocialLinkDto>?
)
