package ru.itis.androidtechpracticeapp.data.api

import retrofit2.http.*
import ru.itis.androidtechpracticeapp.data.api.dto.*
import ru.itis.androidtechpracticeapp.data.api.responses.*
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation


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

    @GET("/users/email?")
    suspend fun getByEmailLike(@Query("q") query: String): List<UserResponse>

    @POST("/group/act/create")
    suspend fun createGroupAct(@Body groupActDto: GroupActDto)

    @POST("/user/act/create")
    suspend fun createUserAct(@Body userActDto: UserActDto)

    @POST("/group/create")
    suspend fun createGroup(@Body groupActDto: GroupDto)

    @GET("/moderator/get-acts/{moderatorId}")
    suspend fun getModeratorActs(@Path("moderatorId") moderatorId: Int): List<ActProofResponse>

    @POST("/user/act/proof/moderator-decision")
    suspend fun sendUserDecision(@Body proof: ProofDecisionDto)

    @POST("/group/act/proof/moderator-decision")
    suspend fun sendGroupDecision(@Body proof: ProofDecisionDto)

    @GET("/user/proofs/approved/{userId}")
    suspend fun getApprovedProofs(@Path("userId") userId: Int): List<ApprovedProofsResponse>

    @GET("/users/top")
    suspend fun getTopUsers(): List<UserPresentation>

    @GET("/chat/find-user/{chatId}/{userId}")
    suspend fun getTitleDialog(
        @Path("chatId") chatId: Int,
        @Path("userId") userId: Int,
    ): TitleDialog

    @POST("/chat/create")
    suspend fun createChat(@Body twoUsersChatDto: TwoUsersChatDto): ChatResponse

    @POST("/sign-up")
    suspend fun signUp(@Body signUpDto: SignUpDto): UserResponse

}