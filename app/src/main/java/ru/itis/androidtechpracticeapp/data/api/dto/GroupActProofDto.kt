package ru.itis.androidtechpracticeapp.data.api.dto

data class GroupActProofDto(
    val groupActId: Int,
    val photoLink: String,
    val text: String,
    val latitude: Double,
    val longitude: Double,
)
