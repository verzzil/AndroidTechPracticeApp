package ru.itis.androidtechpracticeapp.data.api.dto

data class UserActProofDto(
    val userActId: Int,
    val photoLink: String,
    val text: String,
    val latitude: Double,
    val longitude: Double,
)
