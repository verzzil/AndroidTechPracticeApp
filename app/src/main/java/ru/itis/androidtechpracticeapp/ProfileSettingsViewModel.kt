package ru.itis.androidtechpracticeapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.dto.ProfileSettingsDto
import ru.itis.androidtechpracticeapp.domain.usecases.UserUseCase
import javax.inject.Inject

class ProfileSettingsViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val errorMessages: MutableLiveData<String> = MutableLiveData()
    private val changeReady: MutableLiveData<Boolean> = MutableLiveData(false)

    fun changeUserSettings(profileSettingsDto: ProfileSettingsDto, userId: Int) {

        with (profileSettingsDto) {
            if (socialLinks != null) {
                with (socialLinks!![0]) {
                    if (titleOfSocialRecourse == "VK" && !socialLink.startsWith("https://vk.com/")) {
                        errorMessages.value = "Ссылка должна вести на https://vk.com с указанием https://"
                        return
                    }
                    if (titleOfSocialRecourse == "Facebook" && !socialLink.startsWith("https://www.facebook.com/")) {
                        errorMessages.value = "Ссылка должна вести на https://www.facebook.com с указанием https://"
                        return
                    }
                    if (titleOfSocialRecourse == "Instagram" && !socialLink.startsWith("https://www.instagram.com/")) {
                        errorMessages.value = "Ссылка должна вести на https://www.instagram.com с указанием https://"
                        return
                    }
                }
            }
            if (socialLinks?.get(0)?.titleOfSocialRecourse == "Соц сеть") {
                socialLinks = null
            }
            if (lastName == "") lastName = null
            if (firstName == "") firstName = null
            if (oldPassword == "") oldPassword = null
            if (newPassword == "") newPassword = null
            if (photoLink == "") photoLink = null
        }

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userUseCase.changeUserSettings(profileSettingsDto, userId)
                changeReady.postValue(true)
            }
        }
    }

    fun getErrorMessages(): MutableLiveData<String> = errorMessages
    fun getChangeReady(): MutableLiveData<Boolean> = changeReady


}