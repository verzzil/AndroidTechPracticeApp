package ru.itis.androidtechpracticeapp.data.repositories

import ru.itis.androidtechpracticeapp.data.api.dto.*
import ru.itis.androidtechpracticeapp.data.api.responses.ActProofResponse
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse
import ru.itis.androidtechpracticeapp.data.api.responses.ApprovedProofsResponse
import ru.itis.androidtechpracticeapp.data.api.responses.UserResponse
import ru.itis.androidtechpracticeapp.data.models.UserData
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation


interface UsersRepository {

    suspend fun findById(userId: Int): UserData
    suspend fun getByEmailLike(query: String): List<UserData>

    suspend fun signIn(email: String, password: String): TokenDto

    suspend fun changeUserSettings(profileSettingsDto: ProfileSettingsDto, userId: Int)

    suspend fun getActsList(userId: Int): List<ActResponse>
    suspend fun getContinueActsList(userId: Int): List<ActResponse>
    suspend fun getEndActsList(userId: Int): List<ActResponse>

    suspend fun sendActProof(userActProofDto: UserActProofDto)
    suspend fun sendActProof(groupActProofDto: GroupActProofDto)

    suspend fun sendAct(userActDto: UserActDto)
    suspend fun sendAct(groupActDto: GroupActDto)

    suspend fun createGroup(groupDto: GroupDto)

    suspend fun getModeratorActs(moderatorId: Int): List<ActProofResponse>

    suspend fun sendGroupDecision(decision: ProofDecisionDto)
    suspend fun sendUserDecision(decision: ProofDecisionDto)
    suspend fun getApprovedProofs(userId: Int): List<ApprovedProofsResponse>

    suspend fun getTopUsers(): List<UserPresentation>

    suspend fun signUp(signUpDto: SignUpDto): UserResponse

    suspend fun saveFirebaseToken(userId: Int, token: String)

}