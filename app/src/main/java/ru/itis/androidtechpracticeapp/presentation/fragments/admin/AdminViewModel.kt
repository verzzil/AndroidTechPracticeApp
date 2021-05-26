package ru.itis.androidtechpracticeapp.presentation.fragments.admin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.responses.ActProofResponse
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import javax.inject.Inject

class AdminViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val acts: MutableLiveData<List<ActProofResponse>> = MutableLiveData()

    fun getModeratorActs(moderatorId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                acts.postValue(userUseCase.getModeratorActs(moderatorId))
            }
        }
    }

    fun getActs(): MutableLiveData<List<ActProofResponse>> = acts
}