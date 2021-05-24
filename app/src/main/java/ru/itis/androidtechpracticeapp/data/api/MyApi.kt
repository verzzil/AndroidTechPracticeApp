package ru.itis.androidtechpracticeapp.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.itis.androidtechpracticeapp.data.api.dto.SignInDto
import ru.itis.androidtechpracticeapp.data.api.responses.*


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

    @GET("/user/{userId}/chats")
    suspend fun getUserChats(
        @Path("userId") userId: Int
    ) : List<ChatResponse>

    @GET("/chat/{chatId}")
    suspend fun getChatCorrespondence(
        @Path("chatId") chatId: Int
    ) : List<MessageResponse>

    @POST("/sign-in")
    suspend fun signIn(@Body signInDto: SignInDto): SignInResponse

}