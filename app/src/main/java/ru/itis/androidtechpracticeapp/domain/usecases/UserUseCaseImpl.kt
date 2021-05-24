package ru.itis.androidtechpracticeapp.domain.usecases

import ru.itis.androidtechpracticeapp.data.api.dto.TokenDto
import ru.itis.androidtechpracticeapp.data.repositories.UsersRepository

class UserUseCaseImpl(
    private val usersRepository: UsersRepository
) : UserUseCase {

    override suspend fun signIn(email: String, password: String): TokenDto =
        usersRepository.signIn(email, password)

    override suspend fun getById(userId: Int): String {
        val repoResponse = usersRepository.findById(userId)
        return "${repoResponse.firstName} ${repoResponse.lastName}"
    }

}