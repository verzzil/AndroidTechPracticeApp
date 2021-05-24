package ru.itis.androidtechpracticeapp.domain.usecases

import ru.itis.androidtechpracticeapp.data.api.dto.GroupActProofDto
import ru.itis.androidtechpracticeapp.data.api.dto.UserActProofDto
import ru.itis.androidtechpracticeapp.data.repositories.UsersRepository
import javax.inject.Inject

class ActsUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
) : ActsUseCase {

    override suspend fun sendAct(userActProofDto: UserActProofDto) {
        usersRepository.sendAct(userActProofDto)
    }

    override suspend fun sendAct(groupActProofDto: GroupActProofDto) {
        usersRepository.sendAct(groupActProofDto)
    }

}