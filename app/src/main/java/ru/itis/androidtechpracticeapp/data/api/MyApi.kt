package ru.itis.androidtechpracticeapp.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.itis.androidtechpracticeapp.data.api.dto.GroupActProofDto
import ru.itis.androidtechpracticeapp.data.api.dto.ProfileSettingsDto
import ru.itis.androidtechpracticeapp.data.api.dto.SignInDto
import ru.itis.androidtechpracticeapp.data.api.dto.UserActProofDto
import ru.itis.androidtechpracticeapp.data.api.responses.*


interface MyApi {

    @GET("posts/{postId}")
    suspend fun getPostById(
        @Path("postId") postId: Int,
    ): PostResponse

    @GET("posts")
    suspend fun getAllPosts(): List<PostResponse>

    @GET("/users/{userId}")
    suspend fun getUserById(
        @Path("userId") userId: Int,
    ): UserResponse

    @GET("/user/{userId}/chats")
    suspend fun getUserChats(
        @Path("userId") userId: Int,
    ): List<ChatResponse>

    @GET("/chat/{chatId}")
    suspend fun getChatCorrespondence(
        @Path("chatId") chatId: Int,
    ): List<MessageResponse>

    @POST("/sign-in")
    suspend fun signIn(@Body signInDto: SignInDto): SignInResponse

    @POST("/user/change-settings/{userId}")
    suspend fun changeUserSettings(
        @Body profileSettingsDto: ProfileSettingsDto,
        @Path("userId") userId: Int,
    )

    @GET("/user/act/{userId}")
    suspend fun getActsList(@Path("userId") userId: Int): List<ActResponse>

    @GET("/user/act/continue/{userId}")
    suspend fun getContinueActsList(@Path("userId") userId: Int): List<ActResponse>

    @GET("/user/act/end/{userId}")
    suspend fun getEndActsList(@Path("userId") userId: Int): List<ActResponse>

    @GET("/group/{groupId}")
    suspend fun getMainUserFromGroup(@Path("groupId") groupId: Int): GroupMainUserResponse

    @POST("/user/act/proof/create")
    suspend fun createUserProof(@Body userActProofDto: UserActProofDto)

    @POST("/group/act/proof/create")
    suspend fun createGroupProof(@Body groupActProofDto: GroupActProofDto)

}