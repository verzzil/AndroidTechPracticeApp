package ru.itis.androidtechpracticeapp.domain.usecases

import android.util.Log
import ru.itis.androidtechpracticeapp.data.api.dto.*
import ru.itis.androidtechpracticeapp.data.api.responses.ApprovedProofsResponse
import ru.itis.androidtechpracticeapp.data.repositories.UsersRepository
import javax.inject.Inject

class ActsUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
) : ActsUseCase {

    override suspend fun sendActProof(userActProofDto: UserActProofDto) {
        usersRepository.sendActProof(userActProofDto)
    }

    override suspend fun createGroup(groupDto: GroupDto) {
        usersRepository.createGroup(groupDto)
    }

    override suspend fun sendGroupDecision(decision: ProofDecisionDto) {
        usersRepository.sendGroupDecision(decision)
    }

    override suspend fun sendUserDecision(decision: ProofDecisionDto) {
        usersRepository.sendUserDecision(decision)
    }

    override suspend fun getApprovedProofs(userId: Int): List<ApprovedProofsResponse> {
        return usersRepository.getApprovedProofs(userId)
    }

    override suspend fun sendActProof(groupActProofDto: GroupActProofDto) {
        usersRepository.sendActProof(groupActProofDto)
    }

    override suspend fun sendAct(userActDto: UserActDto) {
        usersRepository.sendAct(userActDto)
    }

    override suspend fun sendAct(groupActDto: GroupActDto) {
        usersRepository.sendAct(groupActDto)
    }

}