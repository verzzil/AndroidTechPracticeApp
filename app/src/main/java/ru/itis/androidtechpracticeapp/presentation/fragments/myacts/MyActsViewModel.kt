package ru.itis.androidtechpracticeapp.presentation.fragments.myacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.responses.ActResponse
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import java.lang.Exception
import javax.inject.Inject

class MyActsViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private val list: MutableLiveData<List<ActResponse>> = MutableLiveData()
    private val errors: MutableLiveData<Exception> = MutableLiveData()

    fun searchActs(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    list.postValue(userUseCase.getActsList(userId))
                } catch (e: Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun searchContinueActs(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    list.postValue(userUseCase.getContinueActsList(userId))
                } catch (e: Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun searchEndActs(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    list.postValue(userUseCase.getEndActsList(userId))
                } catch (e: Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun getList(): MutableLiveData<List<ActResponse>> = list
    fun getErrors(): MutableLiveData<Exception> = errors

}