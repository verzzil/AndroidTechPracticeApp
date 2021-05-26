package ru.itis.androidtechpracticeapp.data.api.dto


data class PostDto(
    var authorId: Int,
    var content: String,
    var id: Int?,
    var title: String,
    var link: String
)
