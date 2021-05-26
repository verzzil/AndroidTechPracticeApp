package ru.itis.androidtechpracticeapp.data.api.dto

data class GroupDto(
    val title: String,
    val idMainUser: Int,
    val usersIds: List<Int>
)
