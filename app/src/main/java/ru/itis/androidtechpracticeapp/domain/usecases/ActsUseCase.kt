package ru.itis.androidtechpracticeapp.domain.usecases

import ru.itis.androidtechpracticeapp.data.api.dto.GroupActProofDto
import ru.itis.androidtechpracticeapp.data.api.dto.UserActProofDto

interface ActsUseCase {

    suspend fun sendAct(userActProofDto: UserActProofDto)
    suspend fun sendAct(groupActProofDto: GroupActProofDto)

}