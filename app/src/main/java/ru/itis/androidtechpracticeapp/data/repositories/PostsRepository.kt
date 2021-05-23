package ru.itis.androidtechpracticeapp.data.repositories

import ru.itis.androidtechpracticeapp.data.models.PostData

interface PostsRepository {
    suspend fun findPostById(id: Int) : PostData
    suspend fun findAllPosts(): List<PostData>
}