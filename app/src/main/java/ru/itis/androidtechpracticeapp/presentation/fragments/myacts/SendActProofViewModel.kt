package ru.itis.androidtechpracticeapp.presentation.fragments.myacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.dto.GroupActProofDto
import ru.itis.androidtechpracticeapp.data.api.dto.UserActProofDto
import ru.itis.androidtechpracticeapp.domain.usecases.ActsUseCase
import java.lang.Exception
import javax.inject.Inject

class SendActProofViewModel @Inject constructor(
    private val actsUseCase: ActsUseCase,
) : ViewModel() {

    private val errors: MutableLiveData<Exception> = MutableLiveData()

    fun sendProof(
        actId: Int,
        link: String,
        text: String,
        coords: Pair<Double, Double>,
        actType: String,
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    if (actType == "USER") {
                        actsUseCase.sendActProof(
                            UserActProofDto(
                                actId,
                                link,
                                text,
                                coords.first,
                                coords.second,
                            )
                        )
                    } else {
                        actsUseCase.sendActProof(
                            GroupActProofDto(
                                actId,
                                link,
                                text,
                                coords.first,
                                coords.second
                            )
                        )
                    }
                } catch (e : Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun getErrors(): MutableLiveData<Exception> = errors

}