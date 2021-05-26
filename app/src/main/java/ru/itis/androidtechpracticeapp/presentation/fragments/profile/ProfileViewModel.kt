package ru.itis.androidtechpracticeapp.presentation.fragments.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.domain.usecases.ChatUseCase
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val chatUseCase: ChatUseCase
) : ViewModel() {

    private val currentUser: MutableLiveData<UserPresentation> = MutableLiveData()
    private val chatId: MutableLiveData<Int> = MutableLiveData()

    fun findUser(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                currentUser.postValue(userUseCase.getById(userId))
            }
        }
    }

    fun createChat(myId: Int, anotherUserId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                chatId.postValue(chatUseCase.createChat(myId, anotherUserId))
            }
        }
    }

    fun getCurrentUser(): MutableLiveData<UserPresentation> = currentUser
    fun getChatId(): MutableLiveData<Int> = chatId

}