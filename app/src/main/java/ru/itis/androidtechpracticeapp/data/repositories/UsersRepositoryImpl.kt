package ru.itis.androidtechpracticeapp.data.repositories

import android.accounts.NetworkErrorException
import android.graphics.BitmapFactory
import android.util.Log
import ru.itis.androidtechpracticeapp.data.api.MyApi
import ru.itis.androidtechpracticeapp.data.api.dto.*
import ru.itis.androidtechpracticeapp.data.api.responses.ActProofResponse
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse
import ru.itis.androidtechpracticeapp.data.api.responses.ApprovedProofsResponse
import ru.itis.androidtechpracticeapp.data.api.responses.UserResponse
import ru.itis.androidtechpracticeapp.data.db.dao.UserDao
import ru.itis.androidtechpracticeapp.data.db.models.UserDb
import ru.itis.androidtechpracticeapp.data.models.UserData
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation
import java.net.URL

class UsersRepositoryImpl(
    private val userDao: UserDao,
    private val myApi: MyApi
) : UsersRepository {

    override suspend fun findById(userId: Int): UserData {
        val userResp: UserResponse
        try {
            userResp = myApi.getUserById(userId)
            return UserData.from(userResp)
        } catch (e: Exception) {
            throw NetworkErrorException(e)
        }
    }

    override suspend fun getByEmailLike(query: String): List<UserData> {
        return UserData.fromList(myApi.getByEmailLike(query))
    }

    override suspend fun signUp(signUpDto: SignUpDto): UserResponse {
        return myApi.signUp(signUpDto)
    }

    override suspend fun sendAct(userActDto: UserActDto) {
        myApi.createUserAct(userActDto)
    }

    override suspend fun sendAct(groupActDto: GroupActDto) {
        myApi.createGroupAct(groupActDto)
    }

    override suspend fun createGroup(groupDto: GroupDto) {
        myApi.createGroup(groupDto)
    }

    override suspend fun changeUserSettings(profileSettingsDto: ProfileSettingsDto, userId: Int) {
        myApi.changeUserSettings(profileSettingsDto, userId)
    }

    override suspend fun getActsList(userId: Int): List<ActResponse> {
        val response = myApi.getActsList(userId)
        processData(response, userId)
        return response
    }

    override suspend fun getContinueActsList(userId: Int): List<ActResponse> {
        val response = myApi.getContinueActsList(userId)
        processData(response, userId)
        return response
    }
    override suspend fun getEndActsList(userId: Int): List<ActResponse> {
        val response = myApi.getEndActsList(userId)
        processData(response, userId)
        return response
    }

    override suspend fun sendActProof(userActProofDto: UserActProofDto) {
        myApi.createUserProof(userActProofDto)
    }

    override suspend fun sendActProof(groupActProofDto: GroupActProofDto) {
        myApi.createGroupProof(groupActProofDto)
    }

    override suspend fun signIn(email: String, password: String): TokenDto {
        val response = myApi.signIn(SignInDto(email, password))
        return TokenDto(response.userId, response.token)
    }

    private suspend fun processData(
        response: List<ActResponse>,
        userId: Int,
    ) {
        for (act: ActResponse in response) {
            if (act.type == "USER") {
                act.isAdmin = true
            } else {
                act.isAdmin = userId == myApi.getMainUserFromGroup(act.foreignId).idMainUser
            }
        }
    }

    override suspend fun getModeratorActs(moderatorId: Int): List<ActProofResponse> {
        return myApi.getModeratorActs(moderatorId)
    }

    override suspend fun getApprovedProofs(userId: Int): List<ApprovedProofsResponse> {
        return myApi.getApprovedProofs(userId)
    }

    override suspend fun getTopUsers(): List<UserPresentation> {
        return myApi.getTopUsers()
    }

    override suspend fun sendGroupDecision(decision: ProofDecisionDto) {
        myApi.sendGroupDecision(decision)
    }

    override suspend fun sendUserDecision(decision: ProofDecisionDto) {
        myApi.sendUserDecision(decision)
    }

}