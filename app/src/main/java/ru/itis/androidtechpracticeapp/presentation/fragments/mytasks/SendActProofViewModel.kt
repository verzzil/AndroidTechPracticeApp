package ru.itis.androidtechpracticeapp.presentation.fragments.mytasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.dto.GroupActProofDto
import ru.itis.androidtechpracticeapp.data.api.dto.UserActProofDto
import ru.itis.androidtechpracticeapp.domain.usecases.ActsUseCase
import javax.inject.Inject

class SendActProofViewModel @Inject constructor(
    private val actsUseCase: ActsUseCase,
) : ViewModel() {

    fun sendProof(
        actId: Int,
        link: String,
        text: String,
        coords: Pair<Double, Double>,
        actType: String,
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (actType == "USER") {
                    actsUseCase.sendAct(
                        UserActProofDto(
                            actId,
                            link,
                            text,
                            coords.first,
                            coords.second,
                        )
                    )
                } else {
                    actsUseCase.sendAct(
                        GroupActProofDto(
                            actId,
                            link,
                            text,
                            coords.first,
                            coords.second
                        )
                    )
                }
            }
        }

    }

}