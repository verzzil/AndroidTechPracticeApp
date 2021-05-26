package ru.itis.androidtechpracticeapp.domain.usecases

import ru.itis.androidtechpracticeapp.data.api.dto.*
import ru.itis.androidtechpracticeapp.data.api.responses.ApprovedProofsResponse

interface ActsUseCase {

    suspend fun sendActProof(userActProofDto: UserActProofDto)
    suspend fun sendActProof(groupActProofDto: GroupActProofDto)

    suspend fun sendAct(userActDto: UserActDto)
    suspend fun sendAct(groupActDto: GroupActDto)

    suspend fun createGroup(groupDto: GroupDto)

    suspend fun sendGroupDecision(decision: ProofDecisionDto)
    suspend fun sendUserDecision(decision: ProofDecisionDto)

    suspend fun getApprovedProofs(userId: Int): List<ApprovedProofsResponse>


}