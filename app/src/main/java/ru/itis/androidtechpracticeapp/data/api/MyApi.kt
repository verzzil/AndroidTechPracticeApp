package ru.itis.androidtechpracticeapp.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.itis.androidtechpracticeapp.data.api.responses.PostResponse
import ru.itis.androidtechpracticeapp.data.api.responses.UserResponse


interface MyApi {

    @GET("posts/{postId}")
    suspend fun getPostById(
        @Path("postId") postId: Int
    ) : PostResponse

    @GET("posts")
    suspend fun getAllPosts(): List<PostResponse>

    @GET("/users/{userId}")
    suspend fun getUserById(
        @Path("userId") userId: Int
    ) : UserResponse

}