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

class NewsViewModel @Inject constructor() : ViewModel() {

    private val posts: MutableLiveData<List<PostPresentation>> = MutableLiveData()

    @Inject
    lateinit var postsUseCase: PostUseCase

    fun searchPosts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                posts.postValue(postsUseCase.findAllPosts())
            }
        }
    }

    fun getPosts(): MutableLiveData<List<PostPresentation>> = posts


}