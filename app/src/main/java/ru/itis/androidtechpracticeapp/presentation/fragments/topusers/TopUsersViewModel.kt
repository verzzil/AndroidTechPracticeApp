package ru.itis.androidtechpracticeapp.presentation.fragments.topusers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation
import javax.inject.Inject

class TopUsersViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val topUsers: MutableLiveData<List<UserPresentation>> = MutableLiveData()

    fun findTopUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                topUsers.postValue(userUseCase.getTopUsers())
            }
        }
    }

    fun getTopUsers(): MutableLiveData<List<UserPresentation>> = topUsers

}