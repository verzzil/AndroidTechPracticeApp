package ru.itis.androidtechpracticeapp.data.repositories

import ru.itis.androidtechpracticeapp.data.api.dto.GroupActProofDto
import ru.itis.androidtechpracticeapp.data.api.dto.ProfileSettingsDto
import ru.itis.androidtechpracticeapp.data.api.dto.TokenDto
import ru.itis.androidtechpracticeapp.data.api.dto.UserActProofDto
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse
import ru.itis.androidtechpracticeapp.data.models.UserData


interface UsersRepository {

    suspend fun findById(userId: Int): UserData

    suspend fun signIn(email: String, password: String): TokenDto

    suspend fun changeUserSettings(profileSettingsDto: ProfileSettingsDto, userId: Int)

    suspend fun getActsList(userId: Int): List<ActResponse>
    suspend fun getContinueActsList(userId: Int): List<ActResponse>
    suspend fun getEndActsList(userId: Int): List<ActResponse>

    suspend fun sendAct(userActProofDto: UserActProofDto)
    suspend fun sendAct(groupActProofDto: GroupActProofDto)


}