package ru.itis.androidtechpracticeapp.presentation.fragments.topusers

import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation
import java.net.URL
import javax.inject.Inject

class TopUsersViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val topUsers: MutableLiveData<List<UserPresentation>> = MutableLiveData()
    private val errors: MutableLiveData<Exception> = MutableLiveData()

    fun findTopUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val resp = userUseCase.getTopUsers()
                    for (user: UserPresentation in resp) {
                        if (user.photoLink != null) {
                            user.bitmap =
                                BitmapFactory.decodeStream(URL(user.photoLink).openConnection()
                                    .getInputStream())
                        }
                    }
                    topUsers.postValue(resp)
                } catch (e: Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun getTopUsers(): MutableLiveData<List<UserPresentation>> = topUsers
    fun getErrors(): MutableLiveData<Exception> = errors

}