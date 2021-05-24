package ru.itis.androidtechpracticeapp.domain.usecases

import ru.itis.androidtechpracticeapp.data.api.dto.TokenDto

interface UserUseCase {

    suspend fun signIn(email: String, password: String): TokenDto

    suspend fun getById(userId: Int): String

}