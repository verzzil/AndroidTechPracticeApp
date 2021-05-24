package ru.itis.androidtechpracticeapp.data.api.responses

import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("chatId")
    var chatId: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("sendDate")
    var sendDate: Long,
    @SerializedName("text")
    var text: String,
    @SerializedName("userId")
    var userId: Int,
    @SerializedName("userName")
    var userName: String
)