package ru.itis.androidtechpracticeapp.domain.usecases

import ru.itis.androidtechpracticeapp.data.api.dto.ProfileSettingsDto
import ru.itis.androidtechpracticeapp.data.api.dto.SignUpDto
import ru.itis.androidtechpracticeapp.data.api.dto.TokenDto
import ru.itis.androidtechpracticeapp.data.api.responses.ActProofResponse
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse
import ru.itis.androidtechpracticeapp.data.api.responses.UserResponse
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation

interface UserUseCase {

    suspend fun signIn(email: String, password: String): TokenDto

    suspend fun getById(userId: Int): UserPresentation
    suspend fun getByEmailLike(query: String): List<UserPresentation>
    suspend fun getTopUsers(): List<UserPresentation>

    suspend fun changeUserSettings(profileSettingsDto: ProfileSettingsDto, userId: Int)

    suspend fun getActsList(userId: Int): List<ActResponse>
    suspend fun getContinueActsList(userId: Int): List<ActResponse>
    suspend fun getEndActsList(userId: Int): List<ActResponse>

    suspend fun getModeratorActs(moderatorId: Int): List<ActProofResponse>

    suspend fun signUp(signUpDto: SignUpDto): UserResponse

    suspend fun saveFirebaseToken(userId: Int, token: String)

}