package ru.itis.androidtechpracticeapp.data.repositories

import ru.itis.androidtechpracticeapp.data.api.dto.PostDto
import ru.itis.androidtechpracticeapp.data.models.PostData

interface PostsRepository {
    suspend fun findPostById(id: Int) : PostData
    suspend fun findAllPosts(): List<PostData>

    suspend fun createPost(post: PostDto)
}