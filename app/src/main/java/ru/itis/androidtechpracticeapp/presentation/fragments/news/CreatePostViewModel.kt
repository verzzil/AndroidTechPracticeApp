package ru.itis.androidtechpracticeapp.presentation.fragments.news

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.dto.PostDto
import ru.itis.androidtechpracticeapp.domain.usecases.PostUseCase
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import java.lang.Exception
import javax.inject.Inject

class CreatePostViewModel @Inject constructor(
    private val postUseCase: PostUseCase,
) : ViewModel() {
    private val flag: MutableLiveData<Boolean> = MutableLiveData()
    private val errors: MutableLiveData<Exception> = MutableLiveData()

    fun createPost(title: String, content: String, link: String, userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    postUseCase.createPost(
                        PostDto(userId, content, null, title, link)
                    )
                    flag.postValue(true)
                } catch (e: Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun getFlag(): MutableLiveData<Boolean> = flag
    fun getErrors(): MutableLiveData<Exception> = errors
}