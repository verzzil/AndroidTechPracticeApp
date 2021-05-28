package ru.itis.androidtechpracticeapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation
import javax.inject.Inject

class SharedViewModel @Inject constructor(

) : ViewModel() {

    private var link: MutableLiveData<String> = MutableLiveData()
    private var text: MutableLiveData<String> = MutableLiveData()
    private var coords: MutableLiveData<Pair<Double, Double>> = MutableLiveData()
    private var currentUser: MutableLiveData<UserPresentation> = MutableLiveData()


    fun getLink(): MutableLiveData<String> = link
    fun getText(): MutableLiveData<String> = text
    fun getCoords(): MutableLiveData<Pair<Double, Double>> = coords
    fun getUser(): MutableLiveData<UserPresentation> = currentUser

    fun setUser(user: UserPresentation) {
        currentUser.postValue(user)
    }

    fun setLink(nLink: String) {
        link.value = nLink
    }

    fun setText(nText: String) {
        text.value = nText
    }

    fun setCoords(nCoords: Pair<Double, Double>) {
        coords.value = nCoords
    }

}