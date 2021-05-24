package ru.itis.androidtechpracticeapp.data.repositories

import android.util.Log
import ru.itis.androidtechpracticeapp.data.api.MyApi
import ru.itis.androidtechpracticeapp.data.api.dto.ProfileSettingsDto
import ru.itis.androidtechpracticeapp.data.api.dto.SignInDto
import ru.itis.androidtechpracticeapp.data.api.dto.TokenDto
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse
import ru.itis.androidtechpracticeapp.data.api.responses.UserResponse
import ru.itis.androidtechpracticeapp.data.db.dao.UserDao
import ru.itis.androidtechpracticeapp.data.db.models.UserDb
import ru.itis.androidtechpracticeapp.data.models.UserData

class UsersRepositoryImpl(
    private val userDao: UserDao,
    private val myApi: MyApi
) : UsersRepository {

    override suspend fun findById(userId: Int): UserData {
        val userResp: UserResponse
        try {
            userResp = myApi.getUserById(userId)
        } catch (e: Exception) {
            return UserData.from(userDao.findById(userId))
        }
        userDao.save(UserDb.from(userResp))

        return UserData.from(userDao.findById(userResp.id))
    }

    override suspend fun changeUserSettings(profileSettingsDto: ProfileSettingsDto, userId: Int) {
        myApi.changeUserSettings(profileSettingsDto, userId)
    }

    override suspend fun getActsList(userId: Int): List<ActResponse> {
        val response = myApi.getActsList(userId)
        processData(response, userId)
        Log.i("sdfqerqweasdf", "${response}")
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
                act.isAdmin = userId == myApi.getMainUserFromGroup(act.id).idMainUser
            }
        }
    }

}