package ru.itis.androidtechpracticeapp.presentation.fragments.profile

import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.domain.usecases.ChatUseCase
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation
import java.net.URL
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val chatUseCase: ChatUseCase,
) : ViewModel() {

    private val currentUser: MutableLiveData<UserPresentation> = MutableLiveData()
    private val chatId: MutableLiveData<Int> = MutableLiveData()
    private val errors: MutableLiveData<Exception> = MutableLiveData()

    fun findUser(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val resp = userUseCase.getById(userId)
                    if (resp.photoLink != null) {
                        resp.bitmap =
                            BitmapFactory.decodeStream(URL(resp.photoLink).openConnection()
                                .getInputStream())
                    }
                    currentUser.postValue(resp)
                } catch (e: Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun createChat(myId: Int, anotherUserId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    chatId.postValue(chatUseCase.createChat(myId, anotherUserId))
                } catch (e: Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun getCurrentUser(): MutableLiveData<UserPresentation> = currentUser
    fun getChatId(): MutableLiveData<Int> = chatId
    fun getErrors(): MutableLiveData<Exception> = errors

}