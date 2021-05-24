package ru.itis.androidtechpracticeapp.presentation.fragments.mytasks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import javax.inject.Inject

class MyActsViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val list: MutableLiveData<List<ActResponse>> = MutableLiveData()

    fun searchActs(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                list.postValue(userUseCase.getActsList(userId))
            }
        }
    }

    fun searchContinueActs(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                list.postValue(userUseCase.getContinueActsList(userId))
            }
        }
    }

    fun searchEndActs(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                list.postValue(userUseCase.getEndActsList(userId))
            }
        }
    }

    fun getList(): MutableLiveData<List<ActResponse>> = list

}