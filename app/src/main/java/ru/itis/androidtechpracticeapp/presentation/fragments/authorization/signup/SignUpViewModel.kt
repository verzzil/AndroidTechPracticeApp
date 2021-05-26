package ru.itis.androidtechpracticeapp.presentation.fragments.authorization.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.dto.SignUpDto
import ru.itis.androidtechpracticeapp.data.api.responses.UserResponse
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private val userResp: MutableLiveData<UserResponse> = MutableLiveData()

    fun signUp(fn: String, ln: String, pas: String, email: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userResp.postValue(userUseCase.signUp(SignUpDto(email, fn, ln, pas)))
            }
        }
    }

    fun getUserResp(): MutableLiveData<UserResponse> = userResp

}