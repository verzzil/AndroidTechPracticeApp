package ru.itis.androidtechpracticeapp.presentation.fragments.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidtechpracticeapp.data.api.dto.PostDto
import ru.itis.androidtechpracticeapp.domain.usecases.PostUseCase
import javax.inject.Inject

class CreatePostViewModel @Inject constructor(
    private val postUseCase: PostUseCase,
) : ViewModel() {
    private val flag: MutableLiveData<Boolean> = MutableLiveData()

    fun createPost(title: String, content: String, link: String, userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                postUseCase.createPost(
                    PostDto(userId, content, null, title, link)
                )
                flag.postValue(true)
            }
        }
    }

    fun getFlag(): MutableLiveData<Boolean> = flag
}