package ru.itis.androidtechpracticeapp.presentation.fragments.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.responses.ApprovedProofsResponse
import ru.itis.androidtechpracticeapp.domain.usecases.ActsUseCase
import javax.inject.Inject

class MyMapViewModel @Inject constructor(
    private val actsUseCase: ActsUseCase
): ViewModel() {

    private val points: MutableLiveData<List<ApprovedProofsResponse>> = MutableLiveData()

    fun findApprovedProofs(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                points.postValue(actsUseCase.getApprovedProofs(userId))
            }
        }
    }

    fun getPoints(): MutableLiveData<List<ApprovedProofsResponse>> = points

}