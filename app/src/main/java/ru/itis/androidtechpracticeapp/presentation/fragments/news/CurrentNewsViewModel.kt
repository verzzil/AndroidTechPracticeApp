package ru.itis.androidtechpracticeapp.presentation.fragments.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.domain.usecases.PostUseCase
import ru.itis.androidtechpracticeapp.presentation.models.PostPresentation
import java.lang.Exception
import javax.inject.Inject

class CurrentNewsViewModel @Inject constructor(
    private val postUseCase: PostUseCase,
) : ViewModel() {

    private val post: MutableLiveData<PostPresentation> = MutableLiveData()
    private val errors: MutableLiveData<Exception> = MutableLiveData()

    fun searchPost(postId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    post.postValue(postUseCase.findPostById(postId))
                } catch (e: Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun getPost(): MutableLiveData<PostPresentation> = post
    fun getErrors(): MutableLiveData<Exception> = errors

}