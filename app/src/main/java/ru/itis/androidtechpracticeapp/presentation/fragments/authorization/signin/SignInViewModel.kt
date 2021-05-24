package ru.itis.androidtechpracticeapp.presentation.fragments.authorization.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.dto.TokenDto
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val token: MutableLiveData<TokenDto> = MutableLiveData()
    private val userName: MutableLiveData<String> = MutableLiveData()

    fun signIn(email: String, password: String) {
        if (email == "" || password == "")
            return
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val newToken = userUseCase.signIn(email, password)
                    val newUserName = userUseCase.getById(newToken.userId)
                    userName.postValue(newUserName)
                    token.postValue(newToken)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun getToken(): MutableLiveData<TokenDto> = token

    fun getUserName(): MutableLiveData<String> = userName

}