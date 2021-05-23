package ru.itis.androidtechpracticeapp.domain.usecases

import ru.itis.androidtechpracticeapp.presentation.models.PostPresentation

interface PostUseCase {

    suspend fun findPostById(id: Int) : PostPresentation

    suspend fun findAllPosts(): List<PostPresentation>

}