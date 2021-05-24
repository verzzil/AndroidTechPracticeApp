package ru.itis.androidtechpracticeapp.data.repositories

import ru.itis.androidtechpracticeapp.data.api.dto.TokenDto
import ru.itis.androidtechpracticeapp.data.models.UserData


interface UsersRepository {

    suspend fun findById(userId: Int): UserData

    suspend fun signIn(email: String, password: String): TokenDto

}