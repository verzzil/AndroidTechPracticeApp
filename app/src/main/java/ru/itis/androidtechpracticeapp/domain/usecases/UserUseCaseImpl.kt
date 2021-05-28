package ru.itis.androidtechpracticeapp.domain.usecases

import ru.itis.androidtechpracticeapp.data.api.dto.ProfileSettingsDto
import ru.itis.androidtechpracticeapp.data.api.dto.SignUpDto
import ru.itis.androidtechpracticeapp.data.api.dto.TokenDto
import ru.itis.androidtechpracticeapp.data.api.responses.ActProofResponse
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse
import ru.itis.androidtechpracticeapp.data.api.responses.UserResponse
import ru.itis.androidtechpracticeapp.data.repositories.UsersRepository
import ru.itis.androidtechpracticeapp.domain.models.UserDomain
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation

class UserUseCaseImpl(
    private val usersRepository: UsersRepository,
) : UserUseCase {

    override suspend fun signIn(email: String, password: String): TokenDto =
        usersRepository.signIn(email, password)

    override suspend fun getById(userId: Int): UserPresentation {
        val repoResponse = UserDomain.from(usersRepository.findById(userId))
        return UserPresentation.from(repoResponse)
    }

    override suspend fun saveFirebaseToken(userId: Int, token: String) {
        usersRepository.saveFirebaseToken(userId, token)
    }

    override suspend fun logout(userId: Int, token: String) {
        usersRepository.logout(userId, token)
    }

    override suspend fun getByEmailLike(query: String): List<UserPresentation> {
        val responseRepo = UserDomain.fromList(usersRepository.getByEmailLike(query))
        return UserPresentation.fromList(responseRepo)
    }

    override suspend fun getActsList(userId: Int): List<ActResponse> =
        usersRepository.getActsList(userId)

    override suspend fun signUp(signUpDto: SignUpDto): UserResponse {
        return usersRepository.signUp(signUpDto)
    }

    override suspend fun getModeratorActs(moderatorId: Int): List<ActProofResponse> {
        return usersRepository.getModeratorActs(moderatorId)
    }

    override suspend fun getTopUsers(): List<UserPresentation> {
        return usersRepository.getTopUsers()
    }

    override suspend fun getContinueActsList(userId: Int): List<ActResponse> =
        usersRepository.getContinueActsList(userId)

    override suspend fun getEndActsList(userId: Int): List<ActResponse> =
        usersRepository.getEndActsList(userId)

    override suspend fun changeUserSettings(profileSettingsDto: ProfileSettingsDto, userId: Int) {
        usersRepository.changeUserSettings(profileSettingsDto, userId)
    }

}