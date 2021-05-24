package ru.itis.androidtechpracticeapp.data.api.responses
import com.google.gson.annotations.SerializedName


data class SignInResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("lifetime")
    var lifetime: Any,
    @SerializedName("token")
    var token: String,
    @SerializedName("userId")
    var userId: Int
)