package ru.itis.androidtechpracticeapp.data.api.responses

import com.google.gson.annotations.SerializedName

data class MessageResponseItem(
    @SerializedName("chatId")
    var chatId: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("sendDate")
    var sendDate: Any,
    @SerializedName("text")
    var text: String,
    @SerializedName("userId")
    var userId: Int,
    @SerializedName("userName")
    var userName: String
)