package ru.itis.androidtechpracticeapp.presentation.fragments.admin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.responses.ActProofResponse
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import java.lang.Exception
import javax.inject.Inject

class AdminViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val acts: MutableLiveData<List<ActProofResponse>> = MutableLiveData()
    private val errors: MutableLiveData<Exception> = MutableLiveData()

    fun getModeratorActs(moderatorId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    acts.postValue(userUseCase.getModeratorActs(moderatorId))
                } catch (e : Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun getActs(): MutableLiveData<List<ActProofResponse>> = acts
    fun getErrors(): MutableLiveData<Exception> = errors
}