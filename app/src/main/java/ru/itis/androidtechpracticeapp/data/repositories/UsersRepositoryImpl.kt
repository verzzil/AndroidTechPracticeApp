package ru.itis.androidtechpracticeapp.data.repositories

import ru.itis.androidtechpracticeapp.data.api.MyApi
import ru.itis.androidtechpracticeapp.data.api.responses.UserResponse
import ru.itis.androidtechpracticeapp.data.db.dao.UserDao
import ru.itis.androidtechpracticeapp.data.db.models.UserDb
import ru.itis.androidtechpracticeapp.data.models.UserData
import java.lang.Exception

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

}