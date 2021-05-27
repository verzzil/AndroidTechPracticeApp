package ru.itis.androidtechpracticeapp.presentation

import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation
import java.lang.Exception
import java.net.URL
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private val currentUser: MutableLiveData<UserPresentation> = MutableLiveData()

    fun findUser(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val user = userUseCase.getById(userId)
                if (user.photoLink != null) {
                    try {
                        user.bitmap =
                            BitmapFactory.decodeStream(URL(user.photoLink).openConnection()
                                .getInputStream())
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                currentUser.postValue(user)

            }
        }
    }

    fun getCurrentUser(): MutableLiveData<UserPresentation> = currentUser

}