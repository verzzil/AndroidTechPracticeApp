package ru.itis.androidtechpracticeapp.data.api.responses

import com.google.gson.annotations.SerializedName


data class ChatResponse(
    @SerializedName("chatType")
    var chatType: String,
    @SerializedName("dateOfCreation")
    var dateOfCreation: Long,
    @SerializedName("id")
    var id: Int,
    @SerializedName("lastMessage")
    var lastMessage: MessageResponse,
    @SerializedName("title")
    var title: String,
    @SerializedName("usersId")
    var usersId: List<Int>
)
