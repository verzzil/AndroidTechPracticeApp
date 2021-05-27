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

class NewsViewModel @Inject constructor(
    private val postsUseCase: PostUseCase
) : ViewModel() {

    private val posts: MutableLiveData<List<PostPresentation>> = MutableLiveData()
    private val errors: MutableLiveData<Exception> = MutableLiveData()

    fun searchPosts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val postsResp = postsUseCase.findAllPosts()
                    for (post: PostPresentation in postsResp) {
                        try {
                            post.bitmap = BitmapFactory.decodeStream(URL(post.link).openConnection()
                                .getInputStream())
                        } catch (e: Exception) {
                            continue
                        }
                    }
                    posts.postValue(postsResp)
                } catch (e: Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun getPosts(): MutableLiveData<List<PostPresentation>> = posts
    fun getErrors(): MutableLiveData<Exception> = errors


}