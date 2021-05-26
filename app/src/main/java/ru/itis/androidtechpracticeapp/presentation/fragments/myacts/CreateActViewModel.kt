package ru.itis.androidtechpracticeapp.presentation.fragments.myacts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.dto.GroupActDto
import ru.itis.androidtechpracticeapp.data.api.dto.GroupDto
import ru.itis.androidtechpracticeapp.data.api.dto.UserActDto
import ru.itis.androidtechpracticeapp.domain.usecases.ActsUseCase
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation
import javax.inject.Inject

class CreateActViewModel @Inject constructor(
    private val actsUseCase: ActsUseCase,
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private val users: MutableLiveData<List<UserPresentation>> = MutableLiveData()
    private val selectedUsers: MutableLiveData<Set<UserPresentation>> = MutableLiveData()
    private val listSelUsers: Set<UserPresentation> = HashSet()

    fun findUsers(query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                users.postValue(userUseCase.getByEmailLike(query))
            }
        }
    }

    fun createUserAct(userId: Int, text: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                actsUseCase.sendAct(
                    UserActDto(
                        text,
                        userId
                    )
                )
            }
        }

    }

    fun createGroupAct(usersIds: List<Int>, mainUserId: Int, text: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                actsUseCase.createGroup(GroupDto(text, mainUserId, usersIds))
            }
        }
    }

    fun getUsers(): MutableLiveData<List<UserPresentation>> = users

    fun getSelectedUsers(): MutableLiveData<Set<UserPresentation>> = selectedUsers

    fun setSelectedUsers(user: UserPresentation) {
        (listSelUsers as HashSet).add(user)
        selectedUsers.value = listSelUsers
    }

}