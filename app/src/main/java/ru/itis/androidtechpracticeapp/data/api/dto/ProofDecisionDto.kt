package ru.itis.androidtechpracticeapp.data.api.dto

data class ProofDecisionDto(
    val moderatorId: Int,
    val proofId: Int,
    val decision: String,
    val reward: Int
)
