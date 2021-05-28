package ru.itis.androidtechpracticeapp.presentation.fragments.admin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.data.api.dto.ProofDecisionDto
import ru.itis.androidtechpracticeapp.domain.usecases.ActsUseCase
import java.lang.Exception
import java.net.URL
import javax.inject.Inject

class AdminDecisionViewModel @Inject constructor(
    private val actsUseCase: ActsUseCase,
) : ViewModel() {

    private val errors: MutableLiveData<Exception> = MutableLiveData()
    private val image: MutableLiveData<Bitmap> = MutableLiveData()
    private val imageResource: MutableLiveData<Int> = MutableLiveData()

    fun setDecision(
        actType: String,
        moderatorId: Int,
        proofId: Int,
        decision: String,
        reward: Int,
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val decision = ProofDecisionDto(moderatorId, proofId, decision, reward)
                    if (actType == "GROUP") {
                        actsUseCase.sendGroupDecision(decision)
                    } else {
                        actsUseCase.sendUserDecision(decision)
                    }
                } catch (e: Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun parseImage(link: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    image.postValue(BitmapFactory.decodeStream(URL(link).openConnection()
                        .getInputStream()))
                } catch (e: Exception) {
                    imageResource.postValue(R.drawable.proof_mock)
                }
            }
        }
    }

    fun getErrors(): MutableLiveData<Exception> = errors
    fun getImg(): MutableLiveData<Bitmap> = image
    fun getImgRes(): MutableLiveData<Int> = imageResource

}