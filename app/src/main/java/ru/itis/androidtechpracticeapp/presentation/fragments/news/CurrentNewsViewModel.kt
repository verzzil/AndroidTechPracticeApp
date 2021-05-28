package ru.itis.androidtechpracticeapp.presentation.fragments.news

import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.domain.usecases.PostUseCase
import ru.itis.androidtechpracticeapp.presentation.models.PostPresentation
import java.lang.Exception
import java.net.URL
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
                    val curPost = postUseCase.findPostById(postId)
                    try {
                        curPost.bitmap = BitmapFactory.decodeStream(URL(curPost.link).openConnection()
                            .getInputStream())
                    } catch (e: Exception) {}
                    post.postValue(curPost)
                } catch (e: Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun getPost(): MutableLiveData<PostPresentation> = post
    fun getErrors(): MutableLiveData<Exception> = errors

}