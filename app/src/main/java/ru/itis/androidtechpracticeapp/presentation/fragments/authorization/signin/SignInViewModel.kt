package ru.itis.androidtechpracticeapp.presentation.fragments.authorization.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.dto.TokenDto
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation
import java.lang.Error
import java.lang.Exception
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val token: MutableLiveData<TokenDto> = MutableLiveData()
    private val user: MutableLiveData<UserPresentation> = MutableLiveData()
    private val errors: MutableLiveData<Exception> = MutableLiveData()

    fun signIn(email: String, password: String) {
        if (email == "" || password == "")
            return
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val newToken = userUseCase.signIn(email, password)
                    val newUser = userUseCase.getById(newToken.userId)
                    user.postValue(newUser)
                    token.postValue(newToken)
                } catch (e : Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun getToken(): MutableLiveData<TokenDto> = token

    fun getUserName(): MutableLiveData<UserPresentation> = user

    fun getErrors(): MutableLiveData<Exception> = errors

}