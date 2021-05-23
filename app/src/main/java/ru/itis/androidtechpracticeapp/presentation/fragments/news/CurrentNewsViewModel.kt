package ru.itis.androidtechpracticeapp.presentation.fragments.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.domain.usecases.PostUseCase
import ru.itis.androidtechpracticeapp.presentation.models.PostPresentation
import javax.inject.Inject

class CurrentNewsViewModel @Inject constructor() : ViewModel() {

    private val post: MutableLiveData<PostPresentation> = MutableLiveData()

    @Inject
    lateinit var postUseCase: PostUseCase

    fun searchPost(postId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                post.postValue(postUseCase.findPostById(postId))
            }
        }
    }

    fun getPost(): MutableLiveData<PostPresentation> = post

}