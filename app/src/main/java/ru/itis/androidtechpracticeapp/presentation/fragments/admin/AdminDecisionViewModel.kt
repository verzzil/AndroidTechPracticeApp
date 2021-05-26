package ru.itis.androidtechpracticeapp.presentation.fragments.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.dto.ProofDecisionDto
import ru.itis.androidtechpracticeapp.domain.usecases.ActsUseCase
import javax.inject.Inject

class AdminDecisionViewModel @Inject constructor(
    private val actsUseCase: ActsUseCase
) : ViewModel() {

    fun setDecision(actType: String, moderatorId: Int, proofId: Int, decision: String, reward: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val decision = ProofDecisionDto(moderatorId, proofId, decision, reward)
                if (actType == "GROUP") {
                    actsUseCase.sendGroupDecision(decision)
                } else {
                    actsUseCase.sendUserDecision(decision)
                }
            }
        }
    }

}